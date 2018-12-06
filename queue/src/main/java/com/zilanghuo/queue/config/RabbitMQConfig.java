package com.zilanghuo.queue.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author laiwufa
 * @date 2018/12/3
 * use:
 */
@Configuration
@AutoConfigureBefore(ParameterConfig.class)
public class RabbitMQConfig {

    @Autowired
    private ParameterConfig parameterConfig;

    /**
     * 消息交换机的名字
     */
    public static final String EXCHANGE = "mq-exchange";
    /**
     * 队列key1
     */
    public static final String ROUTINGKEY1 = "queue_one_key1";
    /**
     * 队列key2
     */
    public static final String ROUTINGKEY2 = "queue_one_key2";

    /**
     * 配置链接信息
     *
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(parameterConfig.getRabbitHostName(), parameterConfig.getRabbitPort());
        connectionFactory.setUsername(parameterConfig.getRabbitUserName());
        connectionFactory.setPassword(parameterConfig.getRabbitPassword());
        connectionFactory.setVirtualHost(parameterConfig.getVirtualHost());
        // 必须要设置?
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 配置消息交换机
     * 针对消费者配置
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    /**
     * 配置消息队列1
     * 针对消费者配置
     *
     * @return
     */
    @Bean
    public Queue queue() {
        //队列持久
        return new Queue("queue_one", true);

    }

    /**
     * 将消息队列1与交换机绑定
     * 针对消费者配置
     *
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(RabbitMQConfig.ROUTINGKEY1);
    }

    /**
     * 配置消息队列2
     * 针对消费者配置
     *
     * @return
     */
    @Bean
    public Queue queue1() {
        //队列持久
        return new Queue("queue_one1", true);
    }

    /**
     * 将消息队列2与交换机绑定
     * 针对消费者配置
     *
     * @return
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(defaultExchange()).with(RabbitMQConfig.ROUTINGKEY2);
    }

    /**
     * 接受消息的监听，这个监听会接受消息队列1的消息
     * 针对消费者配置
     *
     * @return
     */
/*    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        //设置确认模式手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            byte[] body = message.getBody();
            System.out.println("收到消息 : " + new String(body));

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费

        });
        return container;
    }*/

    /**
     * 接受消息的监听，这个监听会接受消息队列1的消息
     * 针对消费者配置
     *
     * @return
     */
   /* @Bean
    public SimpleMessageListenerContainer messageContainer2() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue1());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            byte[] body = message.getBody();
            System.out.println("queue1 收到消息 : " + new String(body));
            //确认消息成功消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        });
        return container;
    }*/


}
