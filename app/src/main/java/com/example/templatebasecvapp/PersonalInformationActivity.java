package com.example.templatebasecvapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.templatebasecvapp.Data.*;
import com.example.templatebasecvapp.Data.DbContext;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class PersonalInformationActivity extends AppCompatActivity {
    EditText txtName,txtFatherName,txtCountry,txtDob,txtNationality,txtAddress,txtEmail,txtPhone,txtCnic,txtReligion,txtDomicile;
    RadioButton radioMale,radioFemale,radioMarried,radioUnmarried;
    ImageView addButton,profile;
    Button btnUpdate;
    DbContext db;
    Button btnDob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        db=DbContext.getInstance(getApplicationContext());
        setViewContents();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveChanges("S");
            }
        });
       btnUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SaveChanges("U");
           }
       });
       profile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               intent.setType("image/*");
               startActivityForResult(Intent.createChooser(intent, "Pick image"),
                       1);
           }
       });
        btnDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int  mMonth = c.get(Calendar.MONTH);
                int  mDay = c.get(Calendar.DAY_OF_MONTH);
               DatePickerDialog datePickerDialog = new DatePickerDialog(PersonalInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                txtDob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
   private void SaveChanges(String SaveOrUpdate){
       String imageName=saveImage(((BitmapDrawable)profile.getDrawable()).getBitmap());
       String userName=txtName.getText().toString();
       String fatherName=txtFatherName.getText().toString();
       String country=txtCountry.getText().toString();
       String dob=txtDob.getText().toString();
       String nationality=txtNationality.getText().toString();
       String address=txtAddress.getText().toString();
       String phone=txtPhone.getText().toString();
       String email=txtEmail.getText().toString();
       String religion=txtReligion.getText().toString();
       String cnic=txtCnic.getText().toString();
       String domicile=txtDomicile.getText().toString();
       String gender="Male";
       String martial="Married";
       if(radioFemale.isChecked()){
           gender="Female";
       }
       if(radioUnmarried.isChecked()){
           martial="Unmarried";
       }
       if(userName.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User Name",Toast.LENGTH_LONG).show();
       }else if(fatherName.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User Father name",Toast.LENGTH_LONG).show();
       }
       else if(country.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User Country",Toast.LENGTH_LONG).show();
       }
       else if(dob.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User DOB",Toast.LENGTH_LONG).show();
       }
       else if(nationality.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User Nationality",Toast.LENGTH_LONG).show();
       }
       else if(address.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User Address",Toast.LENGTH_LONG).show();
       }
       else if(email.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User Email",Toast.LENGTH_LONG).show();
       }
       else if(phone.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User phone",Toast.LENGTH_LONG).show();
       }
       else if(religion.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User Religion",Toast.LENGTH_LONG).show();
       }
       else if(domicile.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User Domicile",Toast.LENGTH_LONG).show();
       }
       else if(cnic.length()<1){
           Toast.makeText(getApplicationContext(),"Please Enter User CNIC",Toast.LENGTH_LONG).show();
       }
       else{
           CvDetails.user.setUserPciture(imageName);
           CvDetails.user.setCountry(country);
           CvDetails.user.setUserName(userName);
           CvDetails.user.setDateOfBirth(dob);
           CvDetails.user.setFatherName(fatherName);
           CvDetails.user.setGender(gender);
           CvDetails.user.setMartial(martial);
           CvDetails.user.setNationality(nationality);
           CvDetails.user.setReligion(religion);
           CvDetails.user.setAddress(address);
           CvDetails.user.setEmail(email);
           CvDetails.user.setPhone(phone);
           CvDetails.user.setCnic(cnic);
           CvDetails.user.setDomicile(domicile);
           if(SaveOrUpdate.equals("S")) {
               Log.d("UserData", "" + CvDetails.user.getUserId() + "");
               Intent intent = new Intent(PersonalInformationActivity.this, MianDashboard1Activity.class);
               startActivity(intent);
           }else{
              db.userDao().UpdateUser(CvDetails.user);
              Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
           }
       }
   }
    private void setViewContents() {
        int nextId=db.userDao().GetMaxUserId()+1;
        CvDetails.user.setUserId(nextId);
        btnDob=findViewById(R.id.btnDobDate);
        addButton = findViewById(R.id.addButton);
        profile = findViewById(R.id.profile);
        btnUpdate=findViewById(R.id.updateInfo);
        txtCountry=findViewById(R.id.country);
        txtName=findViewById(R.id.name);
        txtFatherName=findViewById(R.id.fatherName);
        txtDob=findViewById(R.id.dateofbirth);
        radioFemale=findViewById(R.id.radiofemale);
        radioMale=findViewById(R.id.radiomale);
        radioMarried=findViewById(R.id.radioMarried);
        radioUnmarried=findViewById(R.id.radioUnmarried);
        txtAddress=findViewById(R.id.address);
        txtNationality=findViewById(R.id.nationality);
        txtEmail=findViewById(R.id.email);
        txtPhone=findViewById(R.id.phone);
        txtCnic=findViewById(R.id.cnic);
        txtDomicile=findViewById(R.id.domicile);
        txtReligion=findViewById(R.id.religion);
        int userId=getIntent().getIntExtra("UserId",0);
        if(userId>0){
            addButton.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
            CvDetails.user=db.userDao().GetUser(userId);
            txtReligion.setText(CvDetails.user.getReligion());
            txtDomicile.setText(CvDetails.user.getDomicile());
            txtPhone.setText(CvDetails.user.getPhone());
            txtCnic.setText(CvDetails.user.getCnic());
            txtEmail.setText(CvDetails.user.getEmail());
            txtNationality.setText(CvDetails.user.getNationality());
            txtAddress.setText(CvDetails.user.getAddress());
            txtDob.setText(CvDetails.user.getDateOfBirth());
            txtFatherName.setText(CvDetails.user.getFatherName());
            txtName.setText(CvDetails.user.getUserName());
            txtCountry.setText(CvDetails.user.getCountry());
            if(CvDetails.user.getGender().toLowerCase().equals("female")){
                radioFemale.setChecked(true);
            }
            if(CvDetails.user.getMartial().toLowerCase().equals("unmarried")){
                radioUnmarried.setChecked(true);
            }
            DisplayImage(CvDetails.user.getUserPciture());
        }
    }
    private String saveImage(Bitmap finalBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        finalBitmap.compress(Bitmap.CompressFormat.JPEG, 25, outputStream);

        String base64String = Base64.encodeToString(outputStream.toByteArray(),
                Base64.DEFAULT);
        return base64String;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);
                profile.setImageBitmap(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    public void DisplayImage(String imageString){
        try{
            byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            profile.setImageBitmap(decodedByte);}catch (Exception ex){}
    }
}