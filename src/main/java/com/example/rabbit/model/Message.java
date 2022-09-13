package com.example.rabbit.model;

public class Message {
    String messageType;
    String messageBody;

    public Message(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
