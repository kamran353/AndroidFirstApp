package com.example.templatebasecvapp.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.templatebasecvapp.Entities.*;

import java.util.List;
@Dao
public interface UserHobbyDao {
    @Insert
    void AddUserHobbies(List<UserHobby> hobbies);
    @Query("Select * from UserHobby where UserId=:UserId")
    List<UserHobby> GetUserHobbies(int UserId);
    @Delete
    void DeleteUserHobby(UserHobby hobby);
}
