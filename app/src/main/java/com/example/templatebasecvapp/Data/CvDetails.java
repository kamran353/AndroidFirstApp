package com.example.templatebasecvapp.Data;

import com.example.templatebasecvapp.Entities.User;
import com.example.templatebasecvapp.Entities.UserEducation;
import com.example.templatebasecvapp.Entities.UserHobby;
import com.example.templatebasecvapp.Entities.UserJob;
import com.example.templatebasecvapp.Entities.UserProject;
import com.example.templatebasecvapp.Entities.UserSkill;

import java.util.ArrayList;
import java.util.List;

public class CvDetails {
    public  static User user=new User();
    public  static List<UserEducation> userEducationList=new ArrayList<>();
    public  static List<UserJob> userJobList=new ArrayList<>();
    public  static List<UserSkill> userSkillList=new ArrayList<>();
    public  static List<UserHobby> userHobbyList=new ArrayList<>();
    public  static List<UserProject> userProjectList=new ArrayList<>();

    public static void reset() {
        user=new User();
        userEducationList=new ArrayList<>();
        userJobList=new ArrayList<>();
        userSkillList=new ArrayList<>();
        userHobbyList=new ArrayList<>();
        userProjectList=new ArrayList<>();
    }
}
