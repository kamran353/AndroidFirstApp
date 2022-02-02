package com.example.templatebasecvapp.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.templatebasecvapp.Entities.*;

import java.util.List;
@Dao
public interface UserJobDao {
    @Insert
    void AddUserJobs(List<UserJob> jobs);
    @Query("Select * from UserJob where UserId=:UserId")
    List<UserJob> GetUserJobs(int UserId);
    @Delete
    void DeleteUserJob(UserJob userJob);
}
