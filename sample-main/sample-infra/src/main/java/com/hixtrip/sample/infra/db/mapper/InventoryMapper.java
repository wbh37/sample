package com.hixtrip.sample.infra.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hixtrip.sample.infra.db.dataobject.InventoryDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InventoryMapper extends BaseMapper<InventoryDo> {

    InventoryDo getStockBySku(@Param("skuId")String skuId);

}
