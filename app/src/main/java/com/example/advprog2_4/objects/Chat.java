package com.example.advprog2_4.objects;

public class Chat {
    private int id;
    private ChatContact user;
    private LastMessage lastMessage;

    public Chat(int id, ChatContact user, LastMessage lastMessage) {
        this.id = id;
        //this.user = user;
        this.lastMessage = lastMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChatContact getUser() {
        return user;
    }

    public void setUser(ChatContact user) {
        this.user = user;
    }

    public LastMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(LastMessage lastMessage) {
        this.lastMessage = lastMessage;
    }
}
