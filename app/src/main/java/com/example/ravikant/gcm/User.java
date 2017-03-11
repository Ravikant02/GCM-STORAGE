package com.example.ravikant.gcm;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by ravikant on 11/3/17.
 **/

@IgnoreExtraProperties
public class User {
    public String password;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.password = username;
        this.email = email;
    }
}
