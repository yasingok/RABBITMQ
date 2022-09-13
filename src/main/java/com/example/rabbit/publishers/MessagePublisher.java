package com.example.rabbit.publishers;

import com.example.rabbit.RabbitMqInitializer;
import com.example.rabbit.infrastructure.MessageThreadPool;
import com.example.rabbit.service.AmqpMessagePublisher;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class MessagePublisher implements AmqpMessagePublisher {
    Logger logger = LoggerFactory.getLogger(MessagePublisher.class);

    RabbitMqInitializer rabbitMqInitializer;

    public static class LAZY_HOLDER {
        private static final MessagePublisher INSTANCE = new MessagePublisher();
    }

    private MessagePublisher() {
    }

    public static MessagePublisher getInstance() {
        return LAZY_HOLDER.INSTANCE;
    }

    public void setRabbitMqInitializer(RabbitMqInitializer rabbitMqInitializer) {
        this.rabbitMqInitializer = rabbitMqInitializer;
    }

    @Override
    public void sendRequest(String message, String routingKey, String destination) {
        try {
            Channel channel = rabbitMqInitializer.getChannel();
            channel.basicPublish(destination, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.error(String.valueOf(ExceptionUtils.getRootCause(e)));
        }
    }

    @Override
    public void sendSyncRequest(String message, String routingKey, String destination) {
        //MessageThreadPool.getInstance()
    }
}
