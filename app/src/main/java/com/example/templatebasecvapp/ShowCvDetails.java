package com.example.templatebasecvapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.templatebasecvapp.Adapters.*;
import com.example.templatebasecvapp.Adapters.EducationAdapter;
import com.example.templatebasecvapp.Adapters.JobAdapter;
import com.example.templatebasecvapp.Adapters.ProjectAdapter;
import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.*;

import java.util.ArrayList;
import java.util.List;

public class ShowCvDetails extends AppCompatActivity {

    RecyclerView educationRec,projectRec,jobRec;
    DbContext dbContext;
    EducationAdapter educationAdapter;
    ProjectAdapter projectAdapter;
    JobAdapter jobAdapter;
    TextView txtName,txtEmail,txtAddress,txtGender,txtCountry,txtMartialStatus,txtPhoneNumber,txtSkills,txtHobbies,txtContactEmail,txtFatherName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cv_details);
        dbContext=DbContext.getInstance(getApplicationContext());
        setViewContents();
    }

    private void setViewContents() {
        int userId=getIntent().getIntExtra("UserId",0);
        educationRec=findViewById(R.id.recyclerViewEducation);
        educationRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        projectRec=findViewById(R.id.recyclerViewProject);
        projectRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        jobRec=findViewById(R.id.recyclerViewJob);
        jobRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        txtAddress=findViewById(R.id.txtContactAddress);
        txtContactEmail=findViewById(R.id.txtContactEmail);
        txtCountry=findViewById(R.id.txtCountry);
        txtPhoneNumber=findViewById(R.id.txtContactPhone);
        txtEmail=findViewById(R.id.txtEmail);
        txtFatherName=findViewById(R.id.txtPersonalFatherName);
        txtHobbies=findViewById(R.id.txtHobbies);
        txtSkills=findViewById(R.id.txtSkills);
        txtGender=findViewById(R.id.txtPersonalGender);
        txtName=findViewById(R.id.txtName);
        txtMartialStatus=findViewById(R.id.txtPersonalMartial);
        showUserEducation(userId);
        showUserProjects(userId);
        showUserJob(userId);
        showUserSkillsAndHobbies(userId);
        showUserInfo(userId);
    }

    private void showUserInfo(int userId) {
        User user=dbContext.userDao().GetUser(userId);
        txtMartialStatus.setText("Martial Status : "+user.getMartial());
        txtGender.setText("Gender : "+user.getGender());
        txtName.setText(user.getUserName());
        txtFatherName.setText("Father Name :"+user.getFatherName());
        txtEmail.setText(user.getEmail());
        txtContactEmail.setText("Email Address : "+user.getEmail());
        txtAddress.setText("Address : "+user.getAddress());
        txtPhoneNumber.setText("Phone Number : "+user.getPhone());
        txtCountry.setText(user.getCountry());
    }

    private void showUserSkillsAndHobbies(int userId) {
        List<UserSkill> skills=dbContext.userSkillDao().GetUserSkills(userId);
        String txtSkill="";
        for (int i=0;i<skills.size();i++){
            txtSkill=txtSkill+(i+1)+". "+skills.get(i).getSkill()+"\n";
        }
        List<UserHobby> hobbies=dbContext.userHobbyDao().GetUserHobbies(userId);
        String hobby="";
        for (int i=0;i<hobbies.size();i++){
            hobby=hobby+(i+1)+". "+hobbies.get(i).getHobby()+"\n";
        }
        txtSkills.setText(txtSkill);
        txtHobbies.setText(hobby);
    }

    private void showUserProjects(int userId) {

        List<UserProject> userProjectList=dbContext.userProjectDao().GetUserProjects(userId);
        Log.d("Projects",userProjectList.size()+"");
        projectAdapter = new ProjectAdapter(userProjectList,dbContext,false);
        projectRec.setAdapter(projectAdapter);
    }
    private void showUserJob(int userId) {

        List<UserJob> userJobList=dbContext.userJobDao().GetUserJobs(userId);
        jobAdapter=new JobAdapter(userJobList,dbContext,false);
        jobRec.setAdapter(jobAdapter);
    }

    private void showUserEducation(int userId) {
        List<UserEducation> userEducationList=dbContext.userEducationDao().GetUserEducations(userId);
        educationAdapter = new EducationAdapter(userEducationList,dbContext,false);
        educationRec.setAdapter(educationAdapter);
    }
}