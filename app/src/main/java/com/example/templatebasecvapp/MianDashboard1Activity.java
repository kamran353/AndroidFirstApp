package com.example.templatebasecvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.templatebasecvapp.Data.*;
import com.example.templatebasecvapp.Entities.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MianDashboard1Activity extends AppCompatActivity {

    TextView PersonalInformation;
    TextView Qualification;
    TextView JobExperince;
    TextView Projects;
    TextView Skills;
    TextView Hobbies;
    Button btnSave;
    DbContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian_dashboard1);

        PersonalInformation = findViewById(R.id.pinformation);
        Qualification = findViewById(R.id.qualification);
        JobExperince = findViewById(R.id.jobexperince);
        Projects = findViewById(R.id.project);
        Skills = findViewById(R.id.skill);
        Hobbies = findViewById(R.id.hobby);
        btnSave=findViewById(R.id.btnMore);
        dbContext=DbContext.getInstance(getApplicationContext());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userAdded=0;
                if(CvDetails.user.getUserId()>0 && CvDetails.user.getUserName().length()>1) {
                    dbContext.userDao().InsertUser(CvDetails.user);
                    userAdded=1;
                }
                if(CvDetails.userEducationList.size()>0)
                 dbContext.userEducationDao().AddUserEducations(CvDetails.userEducationList);
                if(CvDetails.userProjectList.size()>0)
                 dbContext.userProjectDao().AddUserProjects(CvDetails.userProjectList);
                if(CvDetails.userJobList.size()>0)
                  dbContext.userJobDao().AddUserJobs(CvDetails.userJobList);
                if(CvDetails.userHobbyList.size()>0)
                 dbContext.userHobbyDao().AddUserHobbies(CvDetails.userHobbyList);
                if(CvDetails.userSkillList.size()>0)
                  dbContext.userSkillDao().AddUserSkills(CvDetails.userSkillList);
                if(userAdded==1) {
                    UserCv cv = new UserCv();
                    cv.setUserId(CvDetails.user.getUserId());
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalDateTime time = LocalDateTime.now();
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        dateTimeFormatter.format(time);
                        cv.setCreatedDate(String.valueOf("Created At: " + time));
                    }
                    dbContext.userCvDao().InsertCv(cv);
                    Intent intent = new Intent(MianDashboard1Activity.this, MessageDisplayActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter personal information at least",Toast.LENGTH_LONG).show();
                }
            }
        });
        PersonalInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MianDashboard1Activity.this, PersonalInformationActivity.class);
                intent.putExtra("UserId","0");
                startActivity(intent);
            }
        });
        Qualification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MianDashboard1Activity.this, QualificationInformationActivity.class);
                startActivity(intent);
            }
        });
        JobExperince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MianDashboard1Activity.this, JobExperinceActivity.class);
                startActivity(intent);
            }
        });
        Projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MianDashboard1Activity.this, ProjectsActivity.class);
                startActivity(intent);
            }
        });
        Skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MianDashboard1Activity.this, SkillActivity.class);
                startActivity(intent);
            }
        });
        Hobbies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MianDashboard1Activity.this, HobbiesActivity.class);
                startActivity(intent);
            }
        });
    }
}