package com.example.advprog2_4.objects;

public class PostMessageResponse {
    private int id;
    private String created;
    private ChatContact sender;
    private String content;

    public PostMessageResponse(int id, String created, ChatContact sender, String content) {
        this.id = id;
        this.created = created;
        this.sender = sender;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public ChatContact getSender() {
        return sender;
    }

    public void setSender(ChatContact sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
