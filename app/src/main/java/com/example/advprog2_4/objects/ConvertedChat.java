package com.example.advprog2_4.objects;

public class ConvertedChat {
    private int id;
    private String displayName;
    private String profilePic;
    private String created;

    public ConvertedChat(int id, String displayName, String profilePic, String created) {
        this.id = id;
        this.displayName = displayName;
        this.profilePic = profilePic;
        this.created = created;
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
