package com.example.templatebasecvapp.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.templatebasecvapp.Entities.*;
import com.example.templatebasecvapp.Entities.*;

import java.util.List;
@Dao
public interface UserProjectDao {
    @Insert
    void AddUserProjects(List<UserProject> projects);
    @Query("Select * from UserProject where UserId=:UserId")
    List<UserProject> GetUserProjects(int UserId);
    @Delete
    void DeleteUserProject(UserProject hobby);
}
