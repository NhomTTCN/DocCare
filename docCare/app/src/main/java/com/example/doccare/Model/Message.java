package com.example.doccare.Model;

public class Message {
    Boolean isSeen;
    String message;
    String messageId;
    String receiver;
    String sender;
    String time;
    Boolean isMe;

    public Message(){
    }

    public Message(Boolean isSeen, String message, String messageId, String receiver, String sender, String time, Boolean isMe) {
        this.isSeen = isSeen;
        this.message = message;
        this.messageId = messageId;
        this.receiver = receiver;
        this.sender = sender;
        this.time = time;
        this.isMe = isMe;
    }

    public Boolean getMe() {
        return isMe;
    }

    public void setMe(Boolean me) {
        isMe = me;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
