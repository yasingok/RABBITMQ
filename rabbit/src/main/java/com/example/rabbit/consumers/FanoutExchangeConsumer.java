package com.example.rabbit.consumers;

import com.example.rabbit.service.AmqpMessage;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;

public class FanoutExchangeConsumer implements Consumer {
    AmqpMessage amqpMessage;

    private static class LAZY_HOLDER{
        static final FanoutExchangeConsumer INSTANCE = new FanoutExchangeConsumer();
    }

    public static FanoutExchangeConsumer getInstance(){
        return LAZY_HOLDER.INSTANCE;
    }

    public AmqpMessage getAmqpMessage() {
        return amqpMessage;
    }

    public void setAmqpMessage(AmqpMessage amqpMessage) {
        this.amqpMessage = amqpMessage;
    }

    private FanoutExchangeConsumer(){}
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
        System.out.println("New Fanout Message have been taken");

    }
}
