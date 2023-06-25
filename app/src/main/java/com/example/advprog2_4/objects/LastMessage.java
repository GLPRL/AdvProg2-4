package com.example.advprog2_4.objects;

public class LastMessage {

    private int _id;
    private String created;

    private String content;

    public LastMessage(int _id, String created, String content) {
        this._id = _id;
        this.created = created;
        this.content = content;
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
