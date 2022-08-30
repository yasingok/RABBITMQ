package com.example.rabbit.listeners;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.impl.ErrorOnWriteListener;

import java.io.IOException;

public class RabbitMqErrorListener implements ErrorOnWriteListener {
    @Override
    public void handle(Connection connection, IOException exception) throws IOException {
        System.out.println(connection);
        System.out.println(exception);
    }
}
