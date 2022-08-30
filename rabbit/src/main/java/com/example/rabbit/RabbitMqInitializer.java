package com.example.rabbit;

import com.example.rabbit.common.EnvConstant;
import com.example.rabbit.config.PoolConfig;
import com.example.rabbit.consumers.DirectExchangeConsume;
import com.example.rabbit.consumers.FanoutExchangeConsumer;
import com.example.rabbit.factory.RabbitMqChannelFactory;
import com.example.rabbit.factory.RabbitMqConFactory;
import com.example.rabbit.service.AmqpMessage;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class RabbitMqInitializer {
    GenericObjectPool<Channel> genericObjectPool;
    AmqpMessage amqpMessage;

    public RabbitMqInitializer(AmqpMessage amqpMessage){
        this.amqpMessage = amqpMessage;
        genericObjectPool = new GenericObjectPool<Channel>( RabbitMqChannelFactory.getInstance(), PoolConfig.getInstance().getGenericObjectPoolConfig());
        init();
        setUpExchanges();
        bindDirectQueue();
        bindFanoutQueue();
    }

    private void init(){
        RabbitMqConFactory.getInstance();
        RabbitMqChannelFactory.getInstance();
    }

    public void setUpExchanges(){
        try {
            channelDeclare(EnvConstant.getDirectExchangeName(),EnvConstant.getDirectType());
            channelDeclare(EnvConstant.getFanoutExchangeName(),EnvConstant.getFanoutType());
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getRootCause(e));;
        }
    }

    public void bindDirectQueue(){
        try {
            Channel channel = getChannel();
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EnvConstant.getDirectExchangeName(), EnvConstant.getDirectRouting());
            DirectExchangeConsume directExchangeConsume = DirectExchangeConsume.getInstance();
            directExchangeConsume.setAmqpMessage(this.amqpMessage);
            channel.basicConsume(queueName, true, directExchangeConsume);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bindFanoutQueue(){
        try {
            Channel channel = getChannel();
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EnvConstant.getFanoutExchangeName(), EnvConstant.getFanoutRouting());
            FanoutExchangeConsumer fanoutExchangeConsumer = FanoutExchangeConsumer.getInstance();
            fanoutExchangeConsumer.setAmqpMessage(this.amqpMessage);
            channel.basicConsume(queueName, true, fanoutExchangeConsumer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void channelDeclare(String exchangeName, String exchangeType) throws Exception {
        Channel channel = genericObjectPool.borrowObject();
        channel.exchangeDeclare(exchangeName, exchangeType);
    }


    public Channel getChannel() throws Exception {
        return genericObjectPool.borrowObject();
    }
}
