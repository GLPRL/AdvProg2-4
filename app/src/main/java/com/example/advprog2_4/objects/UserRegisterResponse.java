package com.example.advprog2_4.objects;

public class UserRegisterResponse {
    private String username;
    private String password;
    private String displayName;
    private String profilePic;
    private String _id;
    private String __v;

    public UserRegisterResponse(String username, String password ,String displayName, String profilePic, String _id, String __v) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.profilePic = profilePic;
        this.__v = __v;
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getPassword() {
        return password;
    }
}
