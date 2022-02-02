package com.example.templatebasecvapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.templatebasecvapp.Adapters.CvAdapter;
import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Mappings.UserAndCv;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ShowCVActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DbContext dbContext;
    CvAdapter adapter;
    List<UserAndCv> list;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cvactivity);
        recyclerView=findViewById(R.id.recylerView);
        btnAdd=findViewById(R.id.fl);
        dbContext=DbContext.getInstance(getApplicationContext());
        list=dbContext.userCvDao().GetAllCvsWithUser();
        adapter = new CvAdapter(list,dbContext);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowCVActivity.this, MianDashboard1Activity.class);
                startActivity(intent);
            }
        });
    }
}