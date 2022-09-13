package com.example.rabbit.service;

public interface AmqpMessagePublisher {
    void sendRequest(String message, String routingKey, String destination);
    void sendSyncRequest(String message, String routingKey, String destination);
}
