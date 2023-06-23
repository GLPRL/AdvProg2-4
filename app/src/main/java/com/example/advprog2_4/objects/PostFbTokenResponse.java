package com.example.advprog2_4.objects;

public class PostFbTokenResponse {
    private int _id;
    private String username;
    private String token;
    private int __v;

    public PostFbTokenResponse(int _id, String username, String token, int __v) {
        this._id = _id;
        this.username = username;
        this.token = token;
        this.__v = __v;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
