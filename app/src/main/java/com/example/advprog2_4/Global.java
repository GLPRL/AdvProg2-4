package com.example.advprog2_4;

public class Global {
    private static Global instance;
    private String serverAddress;
    private String token = "";

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
