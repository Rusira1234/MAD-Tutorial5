package com.example.tutorial5_datahandling;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorial5_Database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etUsername , etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
    }

    public void addData(View view){
        DBHelper dbhelper = new DBHelper(this);

        long val = dbhelper.addInfo(etUsername.getText().toString(),etPassword.getText().toString());

        if(val >0){
            Toast.makeText(this,"Data successfully inserted",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Data not inserted",Toast.LENGTH_SHORT).show();
        }
    }
    public void viewAll(View view){
        DBHelper dbhelper = new DBHelper(this);
        List unames = dbhelper.readAllInfo("user");

        Toast.makeText(this,unames.toString(),Toast.LENGTH_SHORT).show();

    }

    public void deleteData(View view){
        DBHelper dbhelper = new DBHelper(this);
        dbhelper.deleteInfo(etUsername.getText().toString());

        Toast.makeText(this,etUsername.getText().toString() + "deleted successfully" , Toast.LENGTH_SHORT).show();
    }

    public void updateData(View view){
        DBHelper dbhelper = new DBHelper(this);
        int val = dbhelper.updateInfo(etUsername.getText().toString(),etPassword.getText().toString());

        if(val > 0){
            Toast.makeText(this , "Data updated successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public void signIn(View view){
        DBHelper dpHelper = new DBHelper(this);

        List usernames = dpHelper.readAllInfo("user");
        List passwords = dpHelper.readAllInfo("password");

        String user = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(usernames.indexOf(user)>= 0){
            if (passwords.get(usernames.indexOf(user)).equals(password)){
                Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show();
            }
        }
    }
}