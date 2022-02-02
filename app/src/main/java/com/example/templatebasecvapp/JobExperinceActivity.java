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
import com.example.templatebasecvapp.Data.CvDetails;
import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.UserEducation;
import com.example.templatebasecvapp.Entities.UserJob;
import com.example.templatebasecvapp.Entities.UserProject;
import com.example.templatebasecvapp.EventClasses.JobEvent;
import com.example.templatebasecvapp.EventClasses.ProjectEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.Locale;

public class JobExperinceActivity extends AppCompatActivity {
    int position=-1;

    ImageView addButton;
    Button btnMore;
    EditText txtCompanyName,txtFromYear,txtToYear,txtTitle,txtAdditional,txtDescription;
    JobAdapter jobAdapter;
    RecyclerView jobRec;
    Button btnChooseFromDate,btnChooseToDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_experince);
        setViewContents();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CvDetails.userJobList.size()>0) {
                    Intent intent = new Intent(JobExperinceActivity.this, MianDashboard1Activity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(),"Please Enter at least one Job",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String company=txtCompanyName.getText().toString();
                String additional=txtAdditional.getText().toString()+"";
                String from=txtFromYear.getText().toString();
                String to=txtToYear.getText().toString();
                String title=txtTitle.getText().toString();
                String description=txtDescription.getText().toString()+"";
                boolean isAlreadyAdded=false;
                for (int i=0;i<CvDetails.userJobList.size();i++){
                    if(CvDetails.userJobList.get(i).getJobTitle().toLowerCase().equals(title.toLowerCase())){
                        isAlreadyAdded=true;
                    }
                }
                if(isAlreadyAdded){
                    Toast.makeText(getApplicationContext(),"You have already Added this Job",Toast.LENGTH_LONG).show();
                }
                else if(title.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Job Title",Toast.LENGTH_LONG).show();
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

               else{
                    UserJob userJob=new UserJob();
                    userJob.setCompanyName(company);
                    userJob.setUserId(CvDetails.user.getUserId());
                    userJob.setFromYear(from);
                    userJob.setToYear(to);
                    userJob.setJobTitle(title);
                    userJob.setAdditionInformation(additional);
                    userJob.setJobDescription(description);
                    if(position!=-1){
                        CvDetails.userJobList.remove(position);
                        CvDetails.userJobList.add(position,userJob);
                        position=-1;
                    }
                     else{ CvDetails.userJobList.add(userJob);}
                    txtTitle.setText("");
                    txtToYear.setText("");
                    txtFromYear.setText("");
                    txtCompanyName.setText("");
                    txtAdditional.setText("");
                    Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
                    jobAdapter.notifyDataSetChanged();
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(JobExperinceActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(JobExperinceActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        btnChooseFromDate=findViewById(R.id.btnFromDate);
        btnChooseToDate=findViewById(R.id.btnToDate);
        txtDescription=findViewById(R.id.description);
        btnMore=findViewById(R.id.btnMore);
        txtFromYear=findViewById(R.id.fromYear);
        txtToYear=findViewById(R.id.toYear);
        txtTitle=findViewById(R.id.jobName);
        txtCompanyName=findViewById(R.id.companyName);
        txtAdditional=findViewById(R.id.additionalInformation);
        addButton = findViewById(R.id.addButton);
        jobRec=findViewById(R.id.recyclerViewJob);
        jobRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showUserJob();
    }

    private void showUserJob() {
        jobAdapter=new JobAdapter(CvDetails.userJobList, DbContext.getInstance(getApplicationContext()),true);
        jobRec.setAdapter(jobAdapter);
    }
    @Subscribe
    public void onCartItemAdd(JobEvent jobEvent){
        this.position=jobEvent.position;
        UserJob up=CvDetails.userJobList.get(jobEvent.position);
        txtCompanyName.setText(up.getCompanyName());
        txtTitle.setText(up.getJobTitle());
        txtAdditional.setText(up.getAdditionInformation());
        txtFromYear.setText(up.getFromYear());
        txtToYear.setText(up.getToYear());
        txtDescription.setText(up.getJobDescription());
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