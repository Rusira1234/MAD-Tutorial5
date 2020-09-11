package com.example.tutorial5_Database;

import android.provider.BaseColumns;

public class UsersMaster {
    private UsersMaster(){}

    public static class Users implements BaseColumns{
        public static final String Table_Name = "Users";
        public static final String Column_Name_UserName = "username";
        public static final String Column_Name_Password = "password";
    }
}
