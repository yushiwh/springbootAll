package com.ys;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.dsl.mail.Mail;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.scheduling.PollerMetadata;

import java.io.File;
import java.io.IOException;

import static java.lang.System.getProperty;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }


    @Value("https://spring.io/blog.atom") // 1
            Resource resource;

    // ***************************读取流程开始*********************//
    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() { // 2
        return Pollers.fixedRate(500).get();

    }

    @Bean
    public FeedEntryMessageSource feedMessageSource() throws IOException { // 3
        // FeedEntryMessageSource实际上是feed:inbound-channel-adapter,此处作为通道的适配器数据输入
        FeedEntryMessageSource messageSource = new FeedEntryMessageSource(resource.getURL(), "news");
        return messageSource;
    }

    @Bean
    public IntegrationFlow myFlow() throws IOException {
        return IntegrationFlows.from(feedMessageSource()) // 4流程从flow开始
                // 5通过路由方法route来选择路由，消息体(PlyLoad)的类型为SyndEntry，作为条件判断类型为String，判断值是通过payload获得的分类
                // 这样的写法需要jdk1.8以上才行
                .<SyndEntry, String>route(payload -> payload.getCategories().get(0).getName(), // 5
                        // 6通过不同的分类的值转向不同的消息通道
                        mapping -> mapping.channelMapping("releases", "releasesChannel") // 6
                                .channelMapping("engineering", "engineeringChannel")
                                .channelMapping("news", "newsChannel"))//注释掉发送邮件

                .get(); // 7通过get方法获得IntegrationFlows的实体，配置的Spring的Bean
    }

    // ******************读取流程结束********************************//

    // *******************Release流程开始***************************//
    @Bean
    public IntegrationFlow releasesFlow() {
        return IntegrationFlows.from(MessageChannels.queue("releasesChannel", 10)) // 1从消息通道releasesChannel开始获取数据
                .<SyndEntry, String>transform(
                        // 2使用transform方法进行数据转换。payload类型为SyndEntry，将其转换成字符串类型，并且自定义数据格式
                        payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator")) // 2
                .handle(Files.outboundAdapter(new File("e:/springblog")) // 3//
                        // 3用handle方法处理file的出战适配器。File类是有Spring
                        // Integration
                        // Java
                        // DSL提供的fluent API用来构造文件的输出的适配器
                        .fileExistsMode(FileExistsMode.APPEND) // 4
                        .charset("UTF-8") // 5
                        .fileNameGenerator(message -> "releases.txt") // 6
                        .get())
                .get();
    }

    // *************engineeringFlow流程*********************//
    @Bean
    public IntegrationFlow engineeringFlow() {
        return IntegrationFlows.from(MessageChannels.queue("engineeringChannel", 10))
                .<SyndEntry, String>transform(
                        payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
                .handle(Files.outboundAdapter(new File("e:/springblog")).fileExistsMode(FileExistsMode.APPEND)
                        .charset("UTF-8").fileNameGenerator(message -> "engineering.txt").get())
                .get();
    }

    // **********************************news流程***********************************
    @Bean
    public IntegrationFlow newsFlow() {
        return IntegrationFlows.from(MessageChannels.queue("newsChannel", 10))
                .<SyndEntry, String>transform(
                        payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
                .enrichHeaders( // 1
                        Mail.headers().subject("来自Spring的新闻").to("94179094@qq.com").from("94179094@qq.com"))
                .handle(Mail.outboundAdapter("smtp.qq.com") // 2
                        .port(25).protocol("smtp").credentials("94179094@qq.com", "******")
                        .javaMailProperties(p -> p.put("mail.debug", "false")), e -> e.id("smtpOut"))
                .get();
    }

}
