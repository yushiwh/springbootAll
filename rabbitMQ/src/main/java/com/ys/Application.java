package com.ys;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    RabbitTemplate rabbitTemplate;//注入springboot配置好的RabbitTemplate


    @Bean//定义目的地及队列，队列名为my-queue
    public Queue ysQueue() {
        return new Queue("my-queue");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }


    @Override
    public void run(String... strings) throws Exception {
        rabbitTemplate.convertAndSend("my-queue", "来自RabbitMQ的问候");//通过rabbitTemplate的convertAndSend方法向my-queue发送消息
    }
}
