package com.example.advprog2_4;

import android.graphics.Bitmap;

import androidx.recyclerview.widget.RecyclerView;

import io.socket.client.Socket;

public class Global {
    private static Global instance;
    private String serverAddress = "";
    private String token = "";
    private String username;
    private String userDisplayName;
    private Bitmap userProfilePic;
    private int currentChatId;
    private ChatDao chatDao;
    private String FBToken = "";
    private Socket socket;
    private RecyclerView recyclerView;
    private RecyclerView chatRecyclerView;
    private String currentChatUsername = "";

    public RecyclerView getChatRecyclerView() {
        return chatRecyclerView;
    }

    public void setChatRecyclerView(RecyclerView chatRecyclerView) {
        this.chatRecyclerView = chatRecyclerView;
    }

    public String getCurrentChatUsername() {
        return currentChatUsername;
    }

    public void setCurrentChatUsername(String currentChatUsername) {
        this.currentChatUsername = currentChatUsername;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getFBToken() {
        return FBToken;
    }

    public void setFBToken(String FBToken) {
        this.FBToken = FBToken;
    }

    public ChatDao getChatDao() {
        return chatDao;
    }

    public void setChatDao(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public Bitmap getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(Bitmap userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    private Bitmap currentContactImage;

    public Bitmap getCurrentContactImage() {
        return currentContactImage;
    }

    public void setCurrentContactImage(Bitmap currentContactImage) {
        this.currentContactImage = currentContactImage;
    }

    public int getCurrentChatId() {
        return currentChatId;
    }

    public void setCurrentChatId(int currentChatId) {
        this.currentChatId = currentChatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public boolean isWasRegisterValid() {
        return wasRegisterValid;
    }

    public void setWasRegisterValid(boolean wasRegisterValid) {
        this.wasRegisterValid = wasRegisterValid;
    }

    private boolean wasRegisterValid = false;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private Global() {
    }

    public static Global getInstance() {
        if (instance == null) {
            synchronized (Global.class) {
                if (instance == null) {
                    instance = new Global();
                }
            }
        }
        return instance;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
