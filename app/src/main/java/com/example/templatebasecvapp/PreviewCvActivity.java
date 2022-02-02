package com.example.templatebasecvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.User;
import com.example.templatebasecvapp.Entities.UserEducation;
import com.example.templatebasecvapp.Entities.UserHobby;
import com.example.templatebasecvapp.Entities.UserJob;
import com.example.templatebasecvapp.Entities.UserProject;
import com.example.templatebasecvapp.Entities.UserSkill;

import java.util.List;

public class PreviewCvActivity extends AppCompatActivity {
    ImageView firstTemplate,secondTemplate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_cv);
        setViewContent();
    }

    private void setViewContent() {
        int userId=getIntent().getIntExtra("UserId",0);
        firstTemplate=findViewById(R.id.firstTemplate);
        secondTemplate=findViewById(R.id.secondTemplate);

        firstTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FirstTemplate.class);
                intent.putExtra("UserId",userId);
                startActivity(intent);
            }
        });
        secondTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SecondTemplate.class);
                intent.putExtra("UserId",userId);
                startActivity(intent);
            }
        });
    }

  }