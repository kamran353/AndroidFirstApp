package com.example.templatebasecvapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.templatebasecvapp.Adapters.EducationAdapter;
import com.example.templatebasecvapp.Adapters.JobAdapter;
import com.example.templatebasecvapp.Data.CvDetails;
import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.UserEducation;
import com.example.templatebasecvapp.Entities.UserProject;
import com.example.templatebasecvapp.EventClasses.EducationEvent;
import com.example.templatebasecvapp.EventClasses.ProjectEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QualificationInformationActivity extends AppCompatActivity {
    int position=-1;
    ImageView addButton;
    Button btnMore;
    EditText txtInstitueName,txtFromYear,txtToYear,txtDescription,txtObtMarks,txtTotalMarks;
    EducationAdapter educationAdapter;
    RecyclerView educationRec;
    Spinner txtType;
    Button btnChooseFromDate,btnChooseToDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualification_information);
        setViewContents();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CvDetails.userEducationList.size()>0) {
                    Intent intent = new Intent(QualificationInformationActivity.this, MianDashboard1Activity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Enter at least one Degree",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String institute=txtInstitueName.getText().toString();
                String from=txtFromYear.getText().toString();
                String to=txtToYear.getText().toString();
                String type=txtType.getSelectedItem().toString();
                String totalMarks=txtTotalMarks.getText().toString();
                String obtMarks=txtObtMarks.getText().toString();
                String description=txtDescription.getText().toString();
                boolean isAlreadyAdded=false;
                for (int i=0;i<CvDetails.userEducationList.size();i++){
                    if(CvDetails.userEducationList.get(i).getType().equals(type)){
                        isAlreadyAdded=true;
                    }
                }
                if(isAlreadyAdded){
                    Toast.makeText(getApplicationContext(),"You have already Added this Degree",Toast.LENGTH_LONG).show();
                }
                else if(institute.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Institute Name",Toast.LENGTH_LONG).show();
                }else if(from.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter From Year",Toast.LENGTH_LONG).show();
                }
                else if(to.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter To Year",Toast.LENGTH_LONG).show();
                }
                else if(type.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Educatiom Type",Toast.LENGTH_LONG).show();
                }
                else if(totalMarks.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Total Marks",Toast.LENGTH_LONG).show();
                }
                else if(obtMarks.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Obtained Marks",Toast.LENGTH_LONG).show();
                }
                else{
                    double total=Double.parseDouble(totalMarks);
                    double obt=Double.parseDouble(obtMarks);
                    double percentage=(obt/total)*100;
                    UserEducation userEducation=new UserEducation();
                    userEducation.setInstitution(institute);
                    userEducation.setUserId(CvDetails.user.getUserId());
                    userEducation.setFromYear(from);
                    userEducation.setToYear(to);
                    userEducation.setType(type);
                    userEducation.setPercentage(percentage);
                    userEducation.setTotalMarks(total);
                    userEducation.setObtainedMarks(obt);
                    userEducation.setDescription(description);
                    if(position!=-1){
                        CvDetails.userEducationList.remove(position);
                        CvDetails.userEducationList.add(position,userEducation);
                        position=-1;
                    }else{
                        CvDetails.userEducationList.add(userEducation);
                    }

                    txtType.setSelection(0);
                    txtToYear.setText("");
                    txtFromYear.setText("");
                    txtInstitueName.setText("");
                    txtObtMarks.setText("");
                    txtTotalMarks.setText("");
                    txtDescription.setText("");
                    Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
                    educationAdapter.notifyDataSetChanged();
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(QualificationInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(QualificationInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        txtType=findViewById(R.id.type);
        txtInstitueName=findViewById(R.id.institueName);
        addButton = findViewById(R.id.addButton);
        educationRec=findViewById(R.id.recyclerViewEducation);
        btnChooseFromDate=findViewById(R.id.btnFromDate);
        btnChooseToDate=findViewById(R.id.btnToDate);
        txtDescription=findViewById(R.id.description);
        txtTotalMarks=findViewById(R.id.totalMarks);
        txtObtMarks=findViewById(R.id.obtMarks);

        List<String> list = new ArrayList<String>();
        list.add("Metric");
        list.add("FSC");
        list.add("BS");
        list.add("MCS");
        list.add("BSC");
        list.add("FA");
        list.add("BA");
        list.add("MA");
        list.add("MS");
        list.add("Ph.d");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtType.setAdapter(dataAdapter);
        txtType.setSelection(0);
        educationRec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showUserJob();
    }

    private void showUserJob() {
        educationAdapter=new EducationAdapter(CvDetails.userEducationList, DbContext.getInstance(getApplicationContext()),true);
        educationRec.setAdapter(educationAdapter);
    }
    @Subscribe
    public void onCartItemAdd(EducationEvent educationEvent){
        this.position=educationEvent.position;
        UserEducation up=CvDetails.userEducationList.get(educationEvent.position);
        txtInstitueName.setText(up.getInstitution());
        txtObtMarks.setText(up.getObtainedMarks()+"");
        txtTotalMarks.setText(up.getTotalMarks()+"");
        txtDescription.setText(up.getDescription()+"");
        if(up.getType().equals("Metric")){
            txtType.setSelection(0);
        }else if(up.getType().equals("FSC")){
            txtType.setSelection(1);
        }
        else if(up.getType().equals("BS")){
            txtType.setSelection(2);
        }
        else if(up.getType().equals("MCS")){
            txtType.setSelection(3);
        }
        else if(up.getType().equals("BSC")){
            txtType.setSelection(4);
        }
        else if(up.getType().equals("FA")){
            txtType.setSelection(5);
        }
        else if(up.getType().equals("BA")){
            txtType.setSelection(6);
        }
        else if(up.getType().equals("MA")){
            txtType.setSelection(7);
        }
        else if(up.getType().equals("MS")){
            txtType.setSelection(8);
        }
        else if(up.getType().equals("Ph.d")){
            txtType.setSelection(8);
        }
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