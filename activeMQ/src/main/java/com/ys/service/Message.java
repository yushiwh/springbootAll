package com.ys.service;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * 定义JMS发送消息需要实现MessageCreator接口，并且重写createMessage方法
 * <p>
 * Created by yushi on 2017/3/8.
 */
public class Message implements MessageCreator {
    @Override
    public javax.jms.Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("测试消息");
    }
}
