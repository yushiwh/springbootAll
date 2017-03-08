Spring Integration 主要解决的是不同系统之间的交互的问题，通过异步消息驱动达到系统交互时系统之间的松耦合
主要由：Message、Channel和Message EndPoint组成


Message：用于不同部分之间传递的数据，
包括
消息体（payload）：可以是任何数据类型。json、xml、java对象等等
消息头（header）：元数据就是解释消息体的内容。


Channel：消息发送者发送消息的通道（Channel），消息接受者从通道接收消息
1、顶级接口：MessageChannel
   子接口
   PollableChannle（可轮询 ）  ：具备轮询消息的能力
   SubscriableChannel（可订阅）：发送消息给订阅者MessageHanlder

2、常用消息通道
   PublishSubscribeChannel:允许广播消息给所有订阅者
   QueueChannel：允许消息接收者轮询获得消息，用一个队列（Queue）接收消息，容量可以进行配置
   PriorityChannel：可按照优先级将数据存储到对，它依据于消息的消息头priority属性
   RendezvousChannel：确保每个接收者收到消息后再发消息
   DirectChannel：默认的信息通道，允许将消息发送给一个订阅者，然后阻碍发送知道消息被接收
   ExecutorChannel：可绑定一个多线程的task executor

3、通道拦截器  ChannelInterceptor  拦截和接收消息的操作
   实现ChannelInterceptor这个接口即可



Message EndPoint：消息的终点 真正处理消息的组件，可以控制通道的路由
1、Channel Adapter  ：通道适配器  连接外部系统或者传输协议的端点，分为入站和出站  这个是单向的
2、Gateway ： 消息网关  提供双向请求/返回的集成，也分为入站和出站
3、Service Activator： 可调用Spring的Bean来处理消息，并将处理后的结果输出到指定的消息通道
4、Router： 路由  可根据消息体的类型、消息头的值以及定义好的接收表。来决定消息传送的通道。
5、filter：过滤器类似于路由，不同的是过滤器不决定消息路由到哪里，而是决定消息是否可以传递给消息通道
6、Splitter：拆分器   将消息拆分成几个部分单独处理，拆分器处理的返回值是一个集合或者数组
7、Aggregator：聚合器和拆分器相反，接收一个List作为参数，将多个消息合并成一个消息
8、Enricher：消息增强器  外部获得消息后，需要增加额外的消息到已有的消息中
9、Transformer：转换器是对获得消息进行一定的逻辑转换处理，如数据格式的转换
10、Bridge：连接桥，简单的讲两个消息通道连接起来



====================================================================================
本例是读取https://spring.io/blog.atom的新闻聚合文件
atom是xml的文件，格式是固定的
会通过分类，将消息转到不同的消息通道，将分类为releases和engineering写到磁盘中，并且通过邮件进行发送


注意项目需要jdk1.8才行




























