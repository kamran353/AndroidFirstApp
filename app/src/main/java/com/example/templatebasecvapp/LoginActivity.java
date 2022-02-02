package com.example.templatebasecvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Field;

public class LoginActivity extends AppCompatActivity {


   EditText txtEmail,txtPassword;
    private Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
       Login = findViewById(R.id.login);
       txtEmail=findViewById(R.id.email);
       txtPassword=findViewById(R.id.password);
       Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEmail.getText().toString().toLowerCase().equals("admin") && txtPassword.getText().toString().equals("1234")) {
                    Intent intent = new Intent(LoginActivity.this, ShowCVActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}