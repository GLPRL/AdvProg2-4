package com.example.advprog2_4.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String token;
    private String username;
    private String displayName;
    private String password;

    @Override
    public String toString() {
        return username;
    }

    private List<Contact> contactList;
    private int profileImg;

    public User(int id, String token, String username, String displayname, String password, int profileImg) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.displayName = displayname;
        this.password = password;
        this.profileImg = profileImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayName;
    }

    public void setDisplayname(String displayname) {
        this.displayName = displayname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }
}
