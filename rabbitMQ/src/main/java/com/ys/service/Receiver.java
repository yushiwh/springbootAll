package com.ys.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听
 * Created by yushi on 2017/3/8.
 */
@Component
public class Receiver {

    @RabbitListener(queues = "my-queue")//监听my-queue的目的地发送的消息
    public void receiveMessage(String message) {

        System.out.println("接受到的消息：-->  <" + message + ">");
    }

}
