package com.example.rabbit.factory;

import com.example.rabbit.common.EnvConstant;
import com.example.rabbit.listeners.RabbitMqErrorListener;
import com.example.rabbit.listeners.RabbitMqExceptionHandler;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConFactory {

    ConnectionFactory connectionFactory = new ConnectionFactory();

    private static class LazyHolder{
        public static final RabbitMqConFactory rabbitMqConFactory = new RabbitMqConFactory();
    }

    public static RabbitMqConFactory getInstance(){
        return LazyHolder.rabbitMqConFactory;
    }

    private RabbitMqConFactory() {
        setConfigurationFactory();
        getConnection();
    }

    private void setConfigurationFactory(){
        this.connectionFactory.setUsername(EnvConstant.getUserName());
        this.connectionFactory.setPassword(EnvConstant.getPassword());
        this.connectionFactory.setPort(EnvConstant.getPort());
        this.connectionFactory.setHost(EnvConstant.getHost());
        this.connectionFactory.setConnectionTimeout(5000);
        this.connectionFactory.setAutomaticRecoveryEnabled(true);
        this.connectionFactory.setErrorOnWriteListener(new RabbitMqErrorListener());
        this.connectionFactory.setExceptionHandler(new RabbitMqExceptionHandler());
    }

    public ConnectionFactory getConnectionFactory(){
        return connectionFactory;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection =  this.connectionFactory.newConnection();
            RabbitMqChannelFactory.getInstance().setConnection(connection);
        } catch (IOException e) {
            System.out.println("IO Exception Occred:"+ ExceptionUtils.getStackTrace(e));
        } catch (TimeoutException e) {
            System.out.println("TimeOut Exception Occred:"+ ExceptionUtils.getStackTrace(e));
        }
        return connection;
    }
}
