要启动docker中的docker run -d -p 61616:61616 -p 8161:8161  cloudesire/activemq


异步消息（发送者无须等待消费者收到处理以及返回，无须关心是否成功）
1：消息代理（message broker）
2：目的地（destination）
目的地分为：
1、队列（Queue）：点对点        每条消息确保只有一个发送者和接收者，但是队列里面可以有多个消息，如果接受到这条消息，队列里面这条消息就没有了。
2、主题（Topic）：发布/订阅式    发布到主题，多个接收者可以监听这个主题，发送方叫发布者   接收方叫接收者



JMS（java消息服务）<-- ActiveMQ是JMS消息代理的实现

AMQP也是消息代理规范，兼容JMS，支持跨平台和语言（RabbitMQ是主要的实现）


AMQ的列子
ActiveMQ的控制台地址
http://10.2.106.168:8161/  admin/admin

启动后会在console中打印出对应的消息
ActiveMQ控制台中也能够显示相应的消息出来