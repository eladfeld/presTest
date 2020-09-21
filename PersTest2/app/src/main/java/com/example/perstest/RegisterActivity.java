package com.example.perstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.persLayer.User;

public class RegisterActivity extends AppCompatActivity {

    protected DatabaseReference mDatabase;
    private EditText usernameInput,emailInput,passwordInput;
    private Button applyRegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_register);
        usernameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        applyRegisterButton = findViewById(R.id.applyRegisterButton);
        applyRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser(usernameInput.getText().toString().trim(),
                        emailInput.getText().toString().trim(),
                        passwordInput.getText().toString().trim());
                finish();
            }
        });
    }

    private void registerNewUser(String username, String email, String password) {
        User user = new User(username, email, password);
        mDatabase.child("users").child(username).child("Personal Information").setValue(user);
    }
}