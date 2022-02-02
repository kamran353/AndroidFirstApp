package com.example.templatebasecvapp.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.templatebasecvapp.Entities.*;

import java.util.List;

@Dao
public interface UserEducationDao {
    @Insert
    void AddUserEducations(List<UserEducation> educations);
    @Query("Select * from UserEducation where UserId=:UserId")
    List<UserEducation> GetUserEducations(int UserId);
    @Delete
    void DeleteUserEducation(UserEducation userEducation);
}
