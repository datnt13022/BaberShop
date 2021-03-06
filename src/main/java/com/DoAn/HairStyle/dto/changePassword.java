package com.DoAn.HairStyle.dto;

public class changePassword {
    private String token;
    private String oldPassword;
    private String newPassword;

    public void setToken(String token) {
        this.token = token;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
