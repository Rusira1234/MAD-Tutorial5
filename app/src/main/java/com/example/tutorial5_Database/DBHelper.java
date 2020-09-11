package com.example.tutorial5_Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UsersInfo.db";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UsersMaster.Users.Table_Name + " (" +
                        UsersMaster.Users._ID +  "INTEGER PRIMARY KEY," +
                        UsersMaster.Users.Column_Name_UserName + "TEXT,"+
                        UsersMaster.Users.Column_Name_Password + "TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int I,int i1){

    }

    public long addInfo(String userName , String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.Column_Name_UserName,userName);
        values.put(UsersMaster.Users.Column_Name_Password,password);

        long newRowId = db.insert(UsersMaster.Users.Table_Name,null,values);
        return newRowId;
    }

    public List readAllInfo(String req) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.Column_Name_UserName,
                UsersMaster.Users.Column_Name_Password
        };

        String sortOrder = UsersMaster.Users.Column_Name_UserName + "DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.Table_Name,
                projection,
                null,
                null,
                null,
                null,
                sortOrder

        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while (cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.Column_Name_UserName));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.Column_Name_Password));
            userNames.add(username);
            passwords.add(password);
        }
        cursor.close();
        if (req == "user") {
            return userNames;
        } else if (req == "password") {
            return passwords;
        } else {
            return null;
        }
    }
        public void deleteInfo(String userName){
            SQLiteDatabase db = getReadableDatabase();
            String selection = UsersMaster.Users.Column_Name_UserName + "LIKE ?";
            String[] selectionArgs = {userName};
            db.delete(UsersMaster.Users.Table_Name,selection,selectionArgs);
        }
        
        public int updateInfo(String userName , String password){
            SQLiteDatabase db = getReadableDatabase();
            ContentValues values = new ContentValues();
            
            values.put(UsersMaster.Users.Column_Name_Password,password);
            
            String selection = UsersMaster.Users.Column_Name_UserName + "LIKE ?";
            String[] selectionArgs = {userName};
            
            int count = db.update{
                UsersMaster.Users.Table_Name,
                values,
                selection,
                selectionArgs
            };
            return count;
    }




}
