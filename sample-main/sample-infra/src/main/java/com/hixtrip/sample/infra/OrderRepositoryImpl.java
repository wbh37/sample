package com.hixtrip.sample.infra;

import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.order.repository.OrderRepository;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import com.hixtrip.sample.infra.db.dataobject.OrderDo;
import com.hixtrip.sample.infra.db.mapper.OrderMapper;
import com.hixtrip.sample.infra.db.util.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private  String generateOrderNumber(){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        int randomNum = new Random().nextInt(999);

        return date+randomNum+ redisTemplate.opsForValue().increment("ORDER_NUMBER");

    }

    @Override
    public void createOrder(Order order) {


        OrderDo orderDo = orderMapper.selectById(order.getId());
        if (orderDo!=null){
            throw new BizException("订单重复提交");
        }

            OrderDo insertOrder = new OrderDo();
        BeanUtils.copyProperties(order,insertOrder);

        insertOrder.setStatus(0);


        orderMapper.insert(insertOrder);

    }

    @Override
    public void orderPaySuccess(CommandPay commandPay) {

        try{

            OrderDo order = orderMapper.selectById(commandPay.getOrderId());
            if (order.getStatus()==1){
                throw new BizException("订单已支付");
            }
            Boolean flag = redisTemplate.opsForValue().setIfAbsent(commandPay.getOrderId(), commandPay.getOrderId(), 10, TimeUnit.SECONDS);

            if (flag){


                LocalDateTime now = LocalDateTime.now();
                OrderDo orderDo = new OrderDo();
                orderDo.setOrderId(commandPay.getOrderId());
                orderDo.setStatus(1);
                orderDo.setPaymentOrderId(commandPay.getPaymentOrderId());
                orderDo.setPayTime(now);
                orderMapper.updateById(orderDo);
            }



        }finally {
            redisTemplate.delete(commandPay.getOrderId());
        }




    }

    @Override
    public void orderPayFail(CommandPay commandPay) {

    }
}
