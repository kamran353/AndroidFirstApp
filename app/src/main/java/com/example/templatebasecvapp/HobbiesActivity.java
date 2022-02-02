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
import com.example.templatebasecvapp.Entities.UserHobby;
import com.example.templatebasecvapp.Entities.UserSkill;

public class HobbiesActivity extends AppCompatActivity {

    EditText txtHobby;
    ImageView addButton;
    Button btnMore;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbies);
        setViewContents();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HobbiesActivity.this, MianDashboard1Activity.class);
                startActivity(intent);
            }

        }); btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hobby=txtHobby.getText().toString();
                if(hobby.length()<1){
                    Toast.makeText(getApplicationContext(),"Please Enter Hobby Title",Toast.LENGTH_LONG).show();
                }
                else{
                    UserHobby userHobby=new UserHobby();
                    userHobby.setHobby(hobby);
                    userHobby.setUserId(CvDetails.user.getUserId());
                    CvDetails.userHobbyList.add(userHobby);
                    txtHobby.setText("");
                    Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
                    DisplayHobbies();
                }
            }
        });
    }

    private void setViewContents() {
        addButton = findViewById(R.id.addButton);
        txtHobby=findViewById(R.id.txtHobby);
        btnMore=findViewById(R.id.btnMore);
        listView=findViewById(R.id.hobbiesList);
        registerForContextMenu(listView);
        DisplayHobbies();
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
                  CvDetails.userHobbyList.remove(pos);
                  DisplayHobbies();
                  txtHobby.setText(title);
                return true;
            case R.id.delete:
                  alertMessage(pos);
                  DisplayHobbies();
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
                        CvDetails.userHobbyList.remove(pos);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    private void DisplayHobbies() {
            String[] hobbies=new String[CvDetails.userHobbyList.size()];
            for (int i=0;i<CvDetails.userHobbyList.size();i++){
                hobbies[i]=CvDetails.userHobbyList.get(i).getHobby();
            }
            ArrayAdapter adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view,R.id.textView, hobbies);
            listView.setAdapter(adapter);
    }
}



