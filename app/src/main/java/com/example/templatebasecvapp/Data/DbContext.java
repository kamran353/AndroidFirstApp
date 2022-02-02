package com.example.templatebasecvapp.Data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.templatebasecvapp.Daos.*;
import com.example.templatebasecvapp.Entities.*;

@Database(entities = {User.class, UserEducation.class, UserProject.class, UserJob.class, UserHobby.class, UserSkill.class, UserCv.class},exportSchema = false,version = 5)
public abstract class DbContext extends RoomDatabase {
    private static final String db_name="Cv_db";
    private static DbContext instance;

    public static synchronized DbContext getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),DbContext.class,db_name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public  abstract UserDao userDao();
    public  abstract UserCvDao userCvDao();
    public  abstract UserEducationDao userEducationDao();
    public  abstract UserHobbyDao userHobbyDao();
    public  abstract UserJobDao userJobDao();
    public  abstract UserProjectDao userProjectDao();
    public  abstract UserSkillDao userSkillDao();
}
