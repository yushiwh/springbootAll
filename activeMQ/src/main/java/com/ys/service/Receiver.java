package com.ys.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by yushi on 2017/3/8.
 */
@Component
public class Receiver {

    @JmsListener(destination = "my-destination")//注解destination属性指定需要监听的目的地即可
    public void receiveMessage(String message) {
        System.out.println("接受到的消息：-->  <" + message + ">");
    }

}
