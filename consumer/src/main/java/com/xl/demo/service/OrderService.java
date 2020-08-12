package com.xl.demo.service;

import com.xl.demo.common.OrderEnum;
import com.xl.demo.entity.Order;
import com.xl.demo.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
@Slf4j
public class OrderService {

    @Resource
    private Sender sender;

    @Resource
    private OrderMapper orderMapper;

    public void order(Order order){
        orderMapper.insert(order);
        genOrderId(order.getId()+"");
    }

    public void genOrderId(String order){
        long times = 30 * 1000;
        sender.send(order, times);
    }

    public void cancelOrder(String orderId){
        try{
            Order order = orderMapper.selectByPrimaryKey(orderId);
            //当订单超过30s没有完成支付，就取消订单
            if(order.getState() == OrderEnum.WAIT.getState()){
                order.setState(OrderEnum.CENCEL.getState());
                orderMapper.updateByPrimaryKey(order);
            }
            log.info("cancel........." + orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
