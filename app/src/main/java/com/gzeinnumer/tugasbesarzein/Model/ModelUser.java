package com.gzeinnumer.tugasbesarzein.Model;

public class ModelUser {
    String email;
    String password;

    public ModelUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
