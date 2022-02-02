package com.example.templatebasecvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.User;
import com.example.templatebasecvapp.Entities.UserEducation;
import com.example.templatebasecvapp.Entities.UserHobby;
import com.example.templatebasecvapp.Entities.UserJob;
import com.example.templatebasecvapp.Entities.UserProject;
import com.example.templatebasecvapp.Entities.UserSkill;

import java.util.List;

public class SecondTemplate extends AppCompatActivity {
    WebView webView;
    DbContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_template);
        setViewContent();
    }
    private void setViewContent() {
        webView=findViewById(R.id.webView);
        dbContext=DbContext.getInstance(getApplicationContext());
        int userId=getIntent().getIntExtra("UserId",0);
        User user=dbContext.userDao().GetUser(userId);
        List<UserHobby> userHobbyList=dbContext.userHobbyDao().GetUserHobbies(userId);
        List<UserSkill> userSkillList=dbContext.userSkillDao().GetUserSkills(userId);
        List<UserEducation> userEducationList=dbContext.userEducationDao().GetUserEducations(userId);
        List<UserProject> userProjectList=dbContext.userProjectDao().GetUserProjects(userId);
        List<UserJob> userJobList=dbContext.userJobDao().GetUserJobs(userId);
        try {
            ShowData(user,userHobbyList,userSkillList,userEducationList,userProjectList,userJobList);
        }catch (Exception ex){
            Log.d("CVShow",ex.toString());
        }


    }
    private void ShowData(User user, List<UserHobby> userHobbyList, List<UserSkill> userSkillList, List<UserEducation> userEducationList, List<UserProject> userProjectList, List<UserJob> userJobList) {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table style=\"text-align: left;border: solid 2px;margin-top: 1px;width: 100%;\">"+
                "<tr>"+
                "<td>"+
                "<div >"+
                "<p style=\"font-size:10px;font-weight:bold;\">Name: "+user.getUserName()+"</p>"+
                "<p style=\"font-size:10px;font-weight:bold;\">Email: "+user.getEmail()+"</p>"+
                "<p style=\"font-size:10px;font-weight:bold;\">Phone: "+user.getPhone()+"</p>"+
                "<p style=\"font-size:10px;font-weight:bold;\">Address: "+user.getAddress()+"</p>"+
                "</div>" +
                "</td>"+
                "<td>"+
                   "<img width=\"100\" height=\"100\" style=\"margin-left:20%;\" src=\"data:image/png;base64,"+user.getUserPciture()+"\" alt=\"No image\">"+
                "</td>"+

            "</tr>"+
          "</table><h3>Projects</h3>");
        if (userProjectList.size()>0) {
            htmlContent.append(
                    "<table style=\"text-align: center;font-size:11px;\" border=\"1\" width=\"100%\">"+
                            "<thead style=\"background-color: skyblue;color: white;width: 100%;\">"+
                            "<tr>"+
                            "<th style=\"width:17%;border: solid 2px;\">Organization</th>"+
                            "<th style=\"width:17%;border: solid 2px;\">Title</th>"+
                            "<th style=\"width:17%;border: solid 2px;\">Start</th>"+
                            "<th style=\"width:17%;border: solid 2px;\">End</th>"+
                            "<th style=\"width:28%;border: solid 2px;\">Description</th>"+
                            "</tr>"+
                            "</thead>"+
                            "<tbody style=\"border: solid 2px;\">");

            for (int i=0;i<userProjectList.size();i++){
                htmlContent.append("<tr>\n" +
                        "<td style=\"width:17%;\">"+userProjectList.get(i).getOrganizationName()+"</td>"+
                        "<td style=\"width:17%;\">"+userProjectList.get(i).getProjectTitle()+"</td>"+
                        "<td style=\"width:17%;\">"+userProjectList.get(i).getFromYear()+"</td>"+
                        "<td style=\"width:17%;\">"+userProjectList.get(i).getToYear()+"</td>"+
                        "<td style=\"width:28%;\">"+userProjectList.get(i).getAdditionInformation()+"</td>"+
                        "</tr>");
            }
            htmlContent.append(" </tbody>\n" +
                    "</table><h3>Education</h3>");
        }
        if (userEducationList.size()>0) {
            htmlContent.append(
                    "<table style=\"text-align: center;font-size:11px;\" border=\"1\" width=\"100%\">"+
                            "<thead style=\"background-color: skyblue;color: white;width: 100%;\">"+
                            "<tr>"+
                            "<th style=\"width:30%;border: solid 2px;\">Institute</th>"+
                            "<th style=\"width:30%;border: solid 2px;\">Degree</th>"+
                            "<th style=\"width:20%;border: solid 2px;\">Start</th>"+
                            "<th style=\"width:20%;border: solid 2px;\">End</th>"+
                            "</tr>"+
                            "</thead>"+
                            "<tbody style=\"border: solid 2px;\">");

            for (int i=0;i<userEducationList.size();i++){
                htmlContent.append("<tr>\n" +
                        "<td style=\"width:17%;\">"+userEducationList.get(i).getInstitution()+"</td>"+
                        "<td style=\"width:17%;\">"+userEducationList.get(i).getType()+"</td>"+
                        "<td style=\"width:17%;\">"+userEducationList.get(i).getFromYear()+"</td>"+
                        "<td style=\"width:17%;\">"+userEducationList.get(i).getToYear()+"</td>"+
                        "</tr>");
            }
            htmlContent.append(" </tbody>\n" +
                    "</table><h3>Experience</h3>");
        }
        if (userJobList.size()>0) {
            htmlContent.append(
                    "<table style=\"text-align: center;font-size:11px;\" border=\"1\" width=\"100%\">"+
                            "<thead style=\"background-color: skyblue;color: white;width: 100%;\">"+
                            "<tr>"+
                            "<th style=\"width:17%;border: solid 2px;\">Organization</th>"+
                            "<th style=\"width:17%;border: solid 2px;\">Title</th>"+
                            "<th style=\"width:17%;border: solid 2px;\">Start</th>"+
                            "<th style=\"width:17%;border: solid 2px;\">End</th>"+
                            "<th style=\"width:28%;border: solid 2px;\">Description</th>"+
                            "</tr>"+
                            "</thead>"+
                            "<tbody style=\"border: solid 2px;\">");

            for (int i=0;i<userProjectList.size();i++){
                htmlContent.append("<tr>\n" +
                        "<td style=\"width:17%;\">"+userJobList.get(i).getCompanyName()+"</td>"+
                        "<td style=\"width:17%;\">"+userJobList.get(i).getJobTitle()+"</td>"+
                        "<td style=\"width:17%;\">"+userJobList.get(i).getFromYear()+"</td>"+
                        "<td style=\"width:17%;\">"+userJobList.get(i).getToYear()+"</td>"+
                        "<td style=\"width:28%;\">"+userJobList.get(i).getAdditionInformation()+"</td>"+
                        "</tr>");
            }
            htmlContent.append(" </tbody>\n" +
                    "</table>"+
                    "</table><h3>Hobbies</h3>");
        }
        if (userSkillList.size()>0) {
            htmlContent.append(
                    "<table style=\"text-align: center;\" border=\"1\" width=\"100%\">"+
                            "<thead style=\"background-color: skyblue;color: white;width: 100%;\">"+
                            "<tr>"+
                            "<th style=\"width:50%;border: solid 2px;\">Sr.No</th>"+
                            "<th style=\"width:50%;border: solid 2px;\">Skill</th>"+
                            "</tr>"+
                            "</thead>"+
                            "<tbody style=\"border: solid 2px;\">");

            for (int i=0;i<userSkillList.size();i++){
                htmlContent.append("<tr>\n" +
                        "<td style=\"width:50%;\">"+(i+1)+"</td>"+
                        "<td style=\"width:50%;\">"+userSkillList.get(i).getSkill()+"<td>"+
                        "</tr>");
            }
            htmlContent.append(" </tbody>\n" +
                    "</table><h3>Hobbies</h3>");
        }
        if (userHobbyList.size()>0) {
            htmlContent.append(
                    "<table style=\"text-align: center;\" border=\"1\" width=\"100%\">"+
                            "<thead style=\"background-color: skyblue;color: white;width: 100%;\">"+
                            "<tr>"+
                            "<th style=\"width:50%;border: solid 2px;\">Sr.No</th>"+
                            "<th style=\"width:50%;border: solid 2px;\">Hobby</th>"+
                            "</tr>"+
                            "</thead>"+
                            "<tbody style=\"border: solid 2px;\">");

            for (int i=0;i<userHobbyList.size();i++){
                htmlContent.append("<tr>\n" +
                        "<td style=\"width:50%;\">"+(i+1)+"</td>"+
                        "<td style=\"width:50%;\">"+userHobbyList.get(i).getHobby()+"<td>"+
                        "</tr>");
            }
            htmlContent.append(" </tbody>\n");
        }
        htmlContent.append(
                "</body>\n" +
                "</html>");

        webView.loadDataWithBaseURL(null, htmlContent.toString(), "text/html", "utf-8", null);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
    }

}