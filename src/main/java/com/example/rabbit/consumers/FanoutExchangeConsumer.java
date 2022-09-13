package com.example.rabbit.consumers;

import com.example.rabbit.service.AmqpMessageReceiver;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FanoutExchangeConsumer implements Consumer {
    Logger logger = LoggerFactory.getLogger(FanoutExchangeConsumer.class);
    AmqpMessageReceiver amqpMessageReceiver;

    private static class LAZY_HOLDER{
        static final FanoutExchangeConsumer INSTANCE = new FanoutExchangeConsumer();
    }

    private FanoutExchangeConsumer(){}

    public static FanoutExchangeConsumer getInstance(){
        return LAZY_HOLDER.INSTANCE;
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
        logger.trace("New Fanout Message have been taken");
        amqpMessageReceiver.consumeFanoutMessage(body);
    }
}
