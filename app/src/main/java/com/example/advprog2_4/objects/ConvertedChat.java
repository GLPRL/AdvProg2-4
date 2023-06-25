package com.example.advprog2_4.objects;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ConvertedChat {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String displayName;
    private String profilePic;
    private String created;
    private String username;

    public ConvertedChat(int id, String displayName, String profilePic, String created, String username) {
        this.id = id;
        this.displayName = displayName;
        this.profilePic = profilePic;
        this.created = created;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
