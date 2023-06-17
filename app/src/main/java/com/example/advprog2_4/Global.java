package com.example.advprog2_4;

public class Global {
    private static Global instance;
    private String serverAddress;
    private String token = "";
    private String username;

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
