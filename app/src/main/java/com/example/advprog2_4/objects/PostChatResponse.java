package com.example.advprog2_4.objects;

public class PostChatResponse {
    private int _id;
    private ChatContact user;

    public PostChatResponse(int id, ChatContact contact) {
        this._id = id;
        this.user = contact;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public ChatContact getContact() {
        return user;
    }

    public void setContact(ChatContact contact) {
        this.user = contact;
    }
}
