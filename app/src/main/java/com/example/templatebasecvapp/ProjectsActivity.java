package com.example.templatebasecvapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.templatebasecvapp.Adapters.JobAdapter;
import com.example.templatebasecvapp.Adapters.ProjectAdapter;
import com.example.templatebasecvapp.Data.CvDetails;
import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.UserCv;
import com.example.templatebasecvapp.Entities.UserEducation;
import com.example.templatebasecvapp.Entities.UserJob;
import com.example.templatebasecvapp.Entities.UserProject;
import com.example.templatebasecvapp.EventClasses.ProjectEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ProjectsActivity extends AppCompatActivity {
    int position;
    Button btnSubmit,btnMore;
    EditText txtCompanyName,txtFromYear,txtToYear,txtTitle,txtAdditional;
    DbContext dbContext;
    ProjectAdapter projectAdapter;
    RecyclerView projectRec;
    Button btnChooseFromDate,btnChooseToDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        setViewContents();
        position=-1;
        dbContext=DbContext.getInstance(getApplicationContext());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(ProjectsActivity.this, MianDashboard1Activity.class);
                 startActivity(intent);
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String company=txtCompanyName.getText().toString();
                String additional=txtAdditional.getText().toString();
                String from=txtFromYear.getText().toString();
                String to=txtToYear.getText().toString();
                String title=txtTitle.getText().toString();
                if(title.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Project Title",Toast.LENGTH_LONG).show();
                }
                else if(company.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Company Name",Toast.LENGTH_LONG).show();
                }
                else if(from.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter From Year",Toast.LENGTH_LONG).show();
                }
                else if(to.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter To Year",Toast.LENGTH_LONG).show();
                }
                else if(additional.length()<1){
                    additional="";
                }
                else{
                    UserProject userProject=new UserProject();
                    userProject.setOrganizationName(company);
                    userProject.setUserId(CvDetails.user.getUserId());
                    userProject.setFromYear(from);
                    userProject.setToYear(to);
                    userProject.setProjectTitle(title);
                    userProject.setAdditionInformation(additional);
                    if(position!=-1){
                        CvDetails.userProjectList.remove(position);
                        CvDetails.userProjectList.add(position,userProject);
                        position=-1;
                    }
                    else{ CvDetails.userProjectList.add(userProject);}
                    txtTitle.setText("");
                    txtToYear.setText("");
                    txtFromYear.setText("");
                    txtCompanyName.setText("");
                    txtAdditional.setText("");
                    Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
                    projectAdapter.notifyDataSetChanged();
                }
            }
        });
        btnChooseFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int  mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProjectsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txtFromYear.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        btnChooseToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int  mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ProjectsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txtToYear.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void setViewContents() {
        btnMore=findViewById(R.id.btnMore);
        txtFromYear=findViewById(R.id.fromYear);
        txtToYear=findViewById(R.id.toYear);
        txtTitle=findViewById(R.id.projectName);
        txtCompanyName=findViewById(R.id.companyName);
        txtAdditional=findViewById(R.id.additionalInformation);
        btnSubmit = findViewById(R.id.btnSubmit);
        projectRec=findViewById(R.id.recyclerViewProject);
        btnChooseFromDate=findViewById(R.id.btnFromDate);
        btnChooseToDate=findViewById(R.id.btnToDate);
        projectRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showUserProjects();
    }

    private void showUserProjects() {
        projectAdapter=new ProjectAdapter(CvDetails.userProjectList, DbContext.getInstance(getApplicationContext()),true);
        projectRec.setAdapter(projectAdapter);
    }
    @Subscribe
    public void onCartItemAdd(ProjectEvent projectEvent){
        this.position=projectEvent.position;
        UserProject up=CvDetails.userProjectList.get(projectEvent.position);
        txtCompanyName.setText(up.getOrganizationName());
        txtTitle.setText(up.getProjectTitle());
        txtAdditional.setText(up.getAdditionInformation());
        txtFromYear.setText(up.getFromYear());
        txtToYear.setText(up.getToYear());
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}