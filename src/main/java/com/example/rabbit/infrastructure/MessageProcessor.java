package com.example.rabbit.infrastructure;

import com.example.rabbit.model.Message;
import com.example.rabbit.service.AmqpMessageReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class MessageProcessor implements Runnable{
    Logger logger = LoggerFactory.getLogger(MessageProcessor.class);
    private final Message message;
    private final AmqpMessageReceiver amqpMessageReceiver;
    public MessageProcessor(AmqpMessageReceiver amqpMessageReceiver, Message message){
        this.amqpMessageReceiver = amqpMessageReceiver;
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public void run() {
        logger.info(Thread.currentThread().getName());
        this.amqpMessageReceiver.consumeDirectMessage(this.getMessage().getMessageBody().getBytes(StandardCharsets.UTF_8));
    }
}
