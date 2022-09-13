package com.example.rabbit.listeners;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ExceptionHandler;
import com.rabbitmq.client.TopologyRecoveryException;

public class RabbitMqExceptionHandler implements ExceptionHandler {
    @Override
    public void handleUnexpectedConnectionDriverException(Connection conn, Throwable exception) {

    }

    @Override
    public void handleReturnListenerException(Channel channel, Throwable exception) {

    }

    @Override
    public void handleConfirmListenerException(Channel channel, Throwable exception) {

    }

    @Override
    public void handleBlockedListenerException(Connection connection, Throwable exception) {

    }

    @Override
    public void handleConsumerException(Channel channel, Throwable exception, Consumer consumer, String consumerTag,
                                        String methodName) {

    }

    @Override
    public void handleConnectionRecoveryException(Connection conn, Throwable exception) {

    }

    @Override
    public void handleChannelRecoveryException(Channel ch, Throwable exception) {

    }

    @Override
    public void handleTopologyRecoveryException(Connection conn, Channel ch, TopologyRecoveryException exception) {

    }
}
