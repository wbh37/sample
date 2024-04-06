package com.hixtrip.sample.infra.db.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "order", autoResultMap = true)
@SuperBuilder(toBuilder = true)
public class OrderDo {



    /**
     *  订单id
     */
    @TableId
    private String orderId;


    /**
     * 支付id
     */
    private String paymentOrderId;

    /**
     * 类型
     */
    private int OrderType;

    /**
     * sku
     */
    private String skuId;

    /**
     * 销售单价
     */
    private BigDecimal salePrice;

    /**
     * 吊牌价
     */
    private BigDecimal originPrice;


    /**
     * 用户id
     */
    private String userId;


    /**
     * 购买总金额
     */
    private BigDecimal totalMoney;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 是否删除（0未删除;1删除
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 删除时间
     */
    private LocalDateTime delTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 订单状态 0、待付款(未支付) 1、待发货(已支付) 2、待收货(已发货) 3、已完成(已签收、待评价) 4、已评价 5、已关闭
     */
    private Integer status;

    /**
     * 收货时间
     */
    private LocalDateTime receivedTime;

    /**
     * 订单关闭时间
     */
    private LocalDateTime closedTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 关联地址id
     */
    private Long address;

    /**
     * 物流公司
     */
    private String expressCompany;

    /**
     * 物流编号
     */
    private String expressCode;

    /**
     * 运费
     */
    private BigDecimal expressMoney;

    /**
     * 优惠券编号
     */
    private String couponCode;

    /**
     * 花费积分
     */
    private BigDecimal integralCost;

    /**
     * 售后状态 0待售后 1售后中 2不能售后 3售后成功
     */
    private Integer afterSaleStatus;


}
