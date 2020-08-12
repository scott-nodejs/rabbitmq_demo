package com.xl.demo.service;

import com.xl.demo.configure.ConfigEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class Sender {

    @Resource
    private AmqpTemplate amqpTemplate;

    public void send(String orderId, final long times){
        log.info("send order : " + orderId);
        amqpTemplate.convertAndSend(ConfigEnum.TTL.getExchange(), ConfigEnum.TTL.getKey(), orderId, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(times));
                return message;
            }
        });
    }
}
