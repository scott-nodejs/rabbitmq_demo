package com.rabbit.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.nio.charset.StandardCharsets;

public class main01 {
    private static final String QUEUE_NAME = "myQueue";

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("39.105.231.100");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //声明一个QUEUE
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg = "我是一个测试";

        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes(StandardCharsets.UTF_8));

        System.out.println("生产者发送消息结束，发送内容为：" + msg);

        channel.close();
        connection.close();
    }
}
