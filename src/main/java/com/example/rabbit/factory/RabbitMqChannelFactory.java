package com.example.rabbit.factory;

import com.example.rabbit.listeners.RabbitMqConfirm;
import com.example.rabbit.listeners.RabbitMqReturn;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class RabbitMqChannelFactory implements PooledObjectFactory<Channel> {
    Logger logger = LoggerFactory.getLogger(RabbitMqChannelFactory.class);
    private Connection connection;

    private static  class LAZY_HOLDER{
        static final RabbitMqChannelFactory INSTANCE = new RabbitMqChannelFactory();
    }

    public static RabbitMqChannelFactory getInstance() {
        return LAZY_HOLDER.INSTANCE;
    }

    private RabbitMqChannelFactory() {
    }



    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PooledObject<Channel> makeObject() throws Exception {
        Channel channel = this.connection.createChannel();
        //channel.addReturnListener(RabbitMqReturn.getInstance());
        //channel.addConfirmListener(RabbitMqConfirm.getInstance());
        return new DefaultPooledObject<Channel>(channel);
    }

    @Override
    public void destroyObject(PooledObject<Channel> p) throws Exception {
        Channel channel = p.getObject();
        if (Objects.nonNull(channel) && channel.isOpen()) {
            try {
                logger.trace("Destroy channel");
                channel.close();
            } catch (Exception e) {
                logger.warn(String.valueOf(ExceptionUtils.getRootCause(e)));
            }
        }
    }

    @Override
    public boolean validateObject(PooledObject<Channel> p) {
        return false;
    }

    @Override
    public void activateObject(PooledObject<Channel> p) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<Channel> p) throws Exception {

    }


}
