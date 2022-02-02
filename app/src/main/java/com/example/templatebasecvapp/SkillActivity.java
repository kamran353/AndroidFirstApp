package com.example.templatebasecvapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.templatebasecvapp.Data.CvDetails;
import com.example.templatebasecvapp.Entities.UserJob;
import com.example.templatebasecvapp.Entities.UserSkill;
import com.google.android.material.textfield.TextInputLayout;

public class SkillActivity extends AppCompatActivity {

    EditText txtSkill;
    ImageView addButton;
    Button btnMore;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);
        setViewContents();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CvDetails.userSkillList.size()>0) {
                    Intent intent = new Intent(SkillActivity.this, MianDashboard1Activity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Please Enter at least one Skill",Toast.LENGTH_LONG).show();
                }
            }

        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String skill=txtSkill.getText().toString();
                if(skill.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Skill Title",Toast.LENGTH_LONG).show();
                }
                else{
                    UserSkill userSkill=new UserSkill();
                    userSkill.setSkill(skill);
                    userSkill.setUserId(CvDetails.user.getUserId());
                    CvDetails.userSkillList.add(userSkill);
                    txtSkill.setText("");
                    Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
                    DisplaySkills();
                }
            }
        });
    }

    private void setViewContents() {
        addButton = findViewById(R.id.addButton);
        txtSkill=findViewById(R.id.txtSkill);
        btnMore=findViewById(R.id.btnMore);
        listView=findViewById(R.id.hobbiesList);
        registerForContextMenu(listView);
        DisplaySkills();
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.hobbiesList) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_menu, menu);
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos=info.position;
        switch(item.getItemId()) {
            case R.id.edit:
                String title=listView.getItemAtPosition(pos).toString();
                CvDetails.userSkillList.remove(pos);
                DisplaySkills();
                txtSkill.setText(title);
                return true;
            case R.id.delete:
                alertMessage(pos);
                DisplaySkills();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void alertMessage(int pos) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        CvDetails.userSkillList.remove(pos);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        Toast.makeText(getApplicationContext(), "No Clicked",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    private void DisplaySkills() {

        String[] skills=new String[CvDetails.userSkillList.size()];
        for (int i=0;i<CvDetails.userSkillList.size();i++){
            skills[i]=CvDetails.userSkillList.get(i).getSkill();
        }
        ArrayAdapter adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view,R.id.textView, skills);
        listView.setAdapter(adapter);


    }
}

