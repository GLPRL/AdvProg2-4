package com.example.advprog2_4;

public class Contact {
    private String displayName;
    private String date;
    int profilePic;

    public Contact(String displayName, String date, int profilePic) {
        this.displayName = displayName;
        this.date = date;
        this.profilePic = profilePic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
