package com.hixtrip.sample.infra.db.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "Inventory", autoResultMap = true)
@SuperBuilder(toBuilder = true)
public class InventoryDo {

    @TableId
    private String skuId;

    private String spuId;

    /**
     * 上架库存
     */
    private Integer shelvesStock;

}
