package com.example.rabbit.consumers;

import com.example.rabbit.infrastructure.MessageProcessor;
import com.example.rabbit.infrastructure.MessageThreadPool;
import com.example.rabbit.model.Message;
import com.example.rabbit.service.AmqpMessageReceiver;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DirectExchangeConsume implements Consumer {
    Logger logger = LoggerFactory.getLogger(DirectExchangeConsume.class);
    AmqpMessageReceiver amqpMessageReceiver;
    MessageThreadPool messageThreadPool;

    private static class LAZY_HOLDER{
        static final DirectExchangeConsume INSTANCE = new DirectExchangeConsume();
    }

    private DirectExchangeConsume(){
        this.messageThreadPool = MessageThreadPool.getInstance();
    }

    public static DirectExchangeConsume getInstance(){
        return DirectExchangeConsume.LAZY_HOLDER.INSTANCE;
    }

    public AmqpMessageReceiver getAmqpMessage() {
        return amqpMessageReceiver;
    }

    public void setAmqpMessage(AmqpMessageReceiver amqpMessageReceiver) {
        this.amqpMessageReceiver = amqpMessageReceiver;
    }

    @Override
    public void handleConsumeOk(String consumerTag) {

    }

    @Override
    public void handleCancelOk(String consumerTag) {

    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

    }

    @Override
    public void handleRecoverOk(String consumerTag) {

    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                               byte[] body) throws IOException {
        logger.trace("New Direct Message have been taken");
        logger.info(Thread.currentThread().getName());
        MessageProcessor messageProcessor = new MessageProcessor(this.getAmqpMessage(), new Message(String.valueOf(body)));
        messageThreadPool.handleMessage(messageProcessor);
        //amqpMessageReceiver.consumeDirectMessage(body);

    }

}
