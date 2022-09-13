package com.example.rabbit.service;

public interface AmqpMessageReceiver {
    public void consumeDirectMessage(byte[] body);

    public void consumeFanoutMessage(byte[] body);

}
