package com.example.rabbit.listeners;

import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

public class RabbitMqConfirm implements ConfirmListener {
    private static class LAZY_HOLDER{
        static final RabbitMqConfirm INSTANCE = new RabbitMqConfirm();
    }

    public static RabbitMqConfirm getInstance() {
        return LAZY_HOLDER.INSTANCE;
    }

    private RabbitMqConfirm() {

    }

    @Override
    public void handleAck(long deliveryTag, boolean multiple) throws IOException {

    }

    @Override
    public void handleNack(long deliveryTag, boolean multiple) throws IOException {

    }
}
