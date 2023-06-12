package com.example.advprog2_4.objects;

import androidx.room.Entity;

@Entity
public class Contact {
    private int id;
    private String displayname;
    private int profileImg;
    private String lastMsg;

    public Contact(int id, String displayname, int profileImg) {
        this.id = id;
        this.displayname = displayname;
        this.profileImg = profileImg;
        this.lastMsg = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    @Override
    public String toString() {
        return profileImg + " " + displayname +
                " " + lastMsg;
    }
}
