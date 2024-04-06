package com.hixtrip.sample.domain.pay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandPay {



    /**
     * 订单编号
     */
    private String orderId;


    /**
     * 支付id
     */
    private String paymentOrderId;



    /**
     * 支付时间
     */
    private LocalDateTime payTime;


    /**
     * 订单状态 0、待付款(未支付) 1、待发货(已支付) 2、待收货(已发货) 3、已完成(已签收、待评价) 4、已评价 5、已关闭
     */
    private Integer status;




}