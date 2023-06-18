package com.example.advprog2_4.objects;

public class MessageItem {
    private SenderGetMessages sender;
    private int _id;
    private String created;
    private String content;

    public MessageItem(SenderGetMessages sender, int _id, String created, String content) {
        this.sender = sender;
        this._id = _id;
        this.created = created;
        this.content = content;
    }

    public SenderGetMessages getSender() {
        return sender;
    }

    public void setSender(SenderGetMessages sender) {
        this.sender = sender;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
