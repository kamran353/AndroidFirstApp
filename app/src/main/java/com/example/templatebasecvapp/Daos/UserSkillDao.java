package com.example.templatebasecvapp.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.templatebasecvapp.Entities.*;
import java.util.List;

@Dao
public interface UserSkillDao {
    @Insert
    void AddUserSkills(List<UserSkill> skills);
    @Query("Select * from UserSkill where UserId=:UserId")
    List<UserSkill> GetUserSkills(int UserId);
    @Delete
    void DeleteUserSkill(UserSkill skill);
}
