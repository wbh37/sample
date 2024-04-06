package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.inventory.repository.InventoryRepository;
import com.hixtrip.sample.infra.db.dataobject.InventoryDo;
import com.hixtrip.sample.infra.db.mapper.InventoryMapper;
import com.hixtrip.sample.infra.db.util.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * infra层是domain定义的接口具体的实现
 */
@Component
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String STOCK_KEY="stock_key:";


    @Override
    public int getInventory(String skuId) {

        //上架库存
        InventoryDo stock = inventoryMapper.getStockBySku(skuId);

        UUID uuid = UUID.randomUUID();
        //预占锁
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(STOCK_KEY+"lock:"+skuId, uuid, 10,TimeUnit.SECONDS);

        int inventory=0;

        try {
            if (flag!=null&&flag){

                inventory=stock.getShelvesStock()-(Integer) redisTemplate.opsForValue().get(STOCK_KEY+skuId);

            }
        }finally {
            if (uuid.equals(redisTemplate.opsForValue().get(STOCK_KEY+"lock:"+skuId))){
                redisTemplate.delete(STOCK_KEY+"lock:"+skuId);
            }

        }


        return inventory;
    }

    @Override
    public boolean updateInventory(String skuId, Long sellableQuantity, Long withholdingQuantity, Long occupiedQuantity) {

        //可售库存
        int inventory = getInventory(skuId);

        if (inventory<occupiedQuantity){
            throw new BizException("库存不足");
        }

        UUID uuid = UUID.randomUUID();

        //获取预占库存
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(STOCK_KEY+"lock:"+skuId, uuid, 10, TimeUnit.SECONDS);

        try {
            if (flag!=null&&flag){

                //设置预占库存
                redisTemplate.opsForValue().set(STOCK_KEY+skuId,occupiedQuantity+sellableQuantity);
            }
        }finally {
            if (uuid.equals(redisTemplate.opsForValue().get(STOCK_KEY+"lock:"+skuId))){
                redisTemplate.delete(STOCK_KEY+"lock:"+skuId);
            }
        }



        return true;
    }
}
