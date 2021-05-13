package com.lut.entity;

import java.util.Objects;



public class AdminEntity {

    private String username;
    private String pwdSalt;
    private String pwdHash;

    public AdminEntity(String username, String pwdSalt, String pwdHash) {
        this.username = username;
        this.pwdSalt = pwdSalt;
        this.pwdHash = pwdHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminEntity that = (AdminEntity) o;
        return getUsername().equals(that.getUsername()) && getPwdSalt().equals(that.getPwdSalt()) && getPwdHash().equals(that.getPwdHash());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPwdSalt(), getPwdHash());
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "username='" + username + '\'' +
                ", pwdSalt='" + pwdSalt + '\'' +
                ", pwdHash='" + pwdHash + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwdSalt() {
        return pwdSalt;
    }

    public void setPwdSalt(String pwdSalt) {
        this.pwdSalt = pwdSalt;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }
}
