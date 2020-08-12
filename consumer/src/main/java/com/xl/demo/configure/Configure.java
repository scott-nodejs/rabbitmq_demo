package com.xl.demo.configure;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {
    @Bean
    public DirectExchange ttlExchange(){
        return ExchangeBuilder.directExchange(ConfigEnum.TTL.getExchange()).durable(true).build();
    }

    @Bean
    public Queue ttlQuene(){
        return QueueBuilder.durable(ConfigEnum.TTL.getName())
                .withArgument("x-dead-letter-exchange", ConfigEnum.ORDER.getExchange())//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", ConfigEnum.ORDER.getKey())//到期后转发的路由键
                .build();
    }

    @Bean
    public DirectExchange orderExchange(){
        return ExchangeBuilder.directExchange(ConfigEnum.ORDER.getExchange()).durable(true).build();
    }

    @Bean
    public Queue orderQuene(){
        return new Queue(ConfigEnum.ORDER.getName());
    }

    @Bean
    public Binding orderBinding(DirectExchange orderExchange, Queue orderQuene){
        return BindingBuilder.bind(orderQuene).to(orderExchange).with(ConfigEnum.ORDER.getKey());
    }

    @Bean
    public Binding ttlBinding(DirectExchange ttlExchange, Queue ttlQuene){
        return BindingBuilder.bind(ttlQuene).to(ttlExchange).with(ConfigEnum.TTL.getKey());
    }
}
