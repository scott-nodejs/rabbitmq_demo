package com.xl.demo.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RabbitListener(queues = "order-quene")
public class Consumer {

    @Resource
    private OrderService orderService;

    @RabbitHandler
    public void consumer(String orderId, Channel channel, Message message) throws Exception{
        orderService.cancelOrder(orderId);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
