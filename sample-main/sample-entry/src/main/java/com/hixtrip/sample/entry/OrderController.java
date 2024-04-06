package com.hixtrip.sample.entry;

import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.app.service.OrderServiceImpl;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.inventory.InventoryDomainService;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo 这是你要实现的
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private InventoryDomainService inventoryDomainService;

    /**
     * todo 这是你要实现的接口
     *
     * @param commandOderCreateDTO 入参对象
     * @return 请修改出参对象
     */
    @PostMapping(path = "/command/order/create")
    public String order(@RequestBody CommandOderCreateDTO commandOderCreateDTO) {
        //登录信息可以在这里模拟
        var userId = "";

        if (userId.isEmpty()){

            return "用户不存在";
        }


        Integer inventory = inventoryDomainService.getInventory(commandOderCreateDTO.getSkuId());
        if (inventory<commandOderCreateDTO.getAmount()){
            return "库存不足";
        }

        Boolean aBoolean = inventoryDomainService.changeInventory(commandOderCreateDTO.getSkuId(), inventory.longValue(), 0l, commandOderCreateDTO.getAmount().longValue());

        if (!aBoolean){
            return "库存预占失败";
        }

        Order order = new Order();
        BeanUtils.copyProperties(commandOderCreateDTO,order);


        orderService.createOrder(order);

        return "创建成功";
    }

    /**
     * todo 这是模拟创建订单后，支付结果的回调通知
     * 【中、高级要求】需要使用策略模式处理至少三种场景：支付成功、支付失败、重复支付(自行设计回调报文进行重复判定)
     *
     * @param commandPayDTO 入参对象
     * @return 请修改出参对象
     */
    @PostMapping(path = "/command/order/pay/callback")
    public String payCallback(@RequestBody CommandPayDTO commandPayDTO) {

        if (commandPayDTO.getPayStatus().equals("已支付")){


            CommandPay pay = new CommandPay();

            pay.setOrderId(commandPayDTO.getOrderId());

            pay.setPaymentOrderId(commandPayDTO.getPaymentOrderId());
            pay.setStatus(1);

            orderService.orderPaySuccess(pay);

        }


        return "";
    }

}
