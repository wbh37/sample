package com.hixtrip.sample.app.api;

import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.model.CommandPay;

/**
 * 订单的service层
 */
public interface OrderService {

    void createOrder(Order order);

    boolean orderPaySuccess(CommandPay commandPay);
}
