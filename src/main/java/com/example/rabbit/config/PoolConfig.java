package com.example.rabbit.config;

import com.rabbitmq.client.Channel;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class PoolConfig{

    GenericObjectPoolConfig<Channel> genericObjectPoolConfig;

    private static class LAZY_HOLDER{
        static final PoolConfig poolConfig = new PoolConfig();
    }

    public static PoolConfig getInstance(){
        return LAZY_HOLDER.poolConfig;
    }
    private PoolConfig(){
        genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        setConfig();
    }

    private void setConfig(){
        int numProcessor = Runtime.getRuntime().availableProcessors();
        // Default config channel pool
        this.genericObjectPoolConfig.setMaxTotal(numProcessor * numProcessor);
        this.genericObjectPoolConfig.setMaxIdle(numProcessor * numProcessor);
        this.genericObjectPoolConfig.setMinIdle(numProcessor);
        this.genericObjectPoolConfig.setNumTestsPerEvictionRun(numProcessor * 2);
        this.genericObjectPoolConfig.setBlockWhenExhausted(false);
        this.genericObjectPoolConfig.setMinEvictableIdleTimeMillis(3600000);
        this.genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(1500000);
    }

    public GenericObjectPoolConfig<Channel> getGenericObjectPoolConfig() {
        return genericObjectPoolConfig;
    }
}
