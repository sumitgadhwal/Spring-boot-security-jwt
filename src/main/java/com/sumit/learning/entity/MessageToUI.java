package com.sumit.learning.entity;

public class MessageToUI {

    public String message;

    String token;

    public MessageToUI()
    {}

    public MessageToUI(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
