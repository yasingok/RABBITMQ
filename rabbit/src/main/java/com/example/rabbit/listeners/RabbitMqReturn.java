package com.example.rabbit.listeners;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;

public class RabbitMqReturn implements ReturnListener {

    private static class LAZZY_HOLDER{
        static final RabbitMqReturn INSTANCE = new RabbitMqReturn();
    }

    public static RabbitMqReturn getInstance() {
        return LAZZY_HOLDER.INSTANCE;
    }

    private RabbitMqReturn() {
    }

    @Override
    public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
                             AMQP.BasicProperties properties, byte[] body) throws IOException {

    }
}
