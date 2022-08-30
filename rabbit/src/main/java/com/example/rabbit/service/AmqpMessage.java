package com.example.rabbit.service;

public interface AmqpMessage {
    public void consumeDirectMessage(byte[] body);

    public void consumeFanoutMessage(byte[] body);

}
