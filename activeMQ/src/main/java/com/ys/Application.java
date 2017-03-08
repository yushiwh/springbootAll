package com.ys;

import com.ys.service.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
public class Application implements CommandLineRunner {//Spring Boot提供CommandLineRunner接口，用于程序启动后执行的代码，重写run方法执行
    @Autowired
    JmsTemplate jmsTemplate;//注入SpringBoot为我们配置好的JmsTemplate的Bean

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        jmsTemplate.send("my-destination", new Message());//通过jmsTemplate的send方法向my-destination的目的地发送消息，也等于在消息代理上面定义一个目的地叫my-destination

    }
}
