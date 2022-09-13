package com.example.rabbit.listeners;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.impl.ErrorOnWriteListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RabbitMqErrorListener implements ErrorOnWriteListener {
    Logger logger = LoggerFactory.getLogger(RabbitMqErrorListener.class);
    @Override
    public void handle(Connection connection, IOException exception) throws IOException {
        logger.trace(String.valueOf(connection));
        logger.trace(String.valueOf(exception));
    }
}
