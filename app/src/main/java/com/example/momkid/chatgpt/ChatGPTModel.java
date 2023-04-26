package com.example.momkid.chatgpt;

public class ChatGPTModel {
    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT = "bot";

    String message;
    String senBy;

    public ChatGPTModel(String message, String senBy) {
        this.message = message;
        this.senBy = senBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenBy() {
        return senBy;
    }

    public void setSenBy(String senBy) {
        this.senBy = senBy;
    }
}
