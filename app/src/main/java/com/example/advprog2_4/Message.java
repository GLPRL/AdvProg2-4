package com.example.advprog2_4;

public class Message {
    private String text;
    private int side;

    public Message(String text, int side) {
        this.text = text;
        this.side = side;
    }

    public String getText() {
        return this.text;
    }

    public int getSide() {
        return this.side;
    }
}
