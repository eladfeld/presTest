package com.example.perstest;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.persLayer.FireBase;
import com.persLayer.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.EventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    protected DatabaseReference mDatabase;
    private EditText passwordText, usernameText;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordText = findViewById(R.id.passwordText);
        usernameText = findViewById(R.id.usernameText);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "username: "+ usernameText.getText().toString().trim()+"password: "+passwordText.getText().toString().trim());
            }
        });
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
                Log.d(TAG, "username: "+ usernameText.getText().toString().trim()+"password: "+passwordText.getText().toString().trim());
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void verifyUser(final String username, final String password) {
        if(username == null | password == null)throw new IllegalArgumentException("username or passowrd are null");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/"+ username+"/Personal Information");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user.getPassword().equals(password)) {
                    Log.d(TAG, "onDataChange: " + user + " 11111111111111111111111111111111111111111");
                    openConectedActivity();
                }
                else
                    Log.d(TAG, "onDataChange: "+ "wrong email 111111111111111111111111111111111111111111111111"+user);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onDataChange: " + "errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            }
        });
    }

    private void openConectedActivity() {
        Intent intent = new Intent(this,ConnectedActicity.class);
        startActivity(intent);
    }
}