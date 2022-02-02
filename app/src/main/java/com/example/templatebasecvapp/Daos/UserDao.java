package com.example.templatebasecvapp.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.templatebasecvapp.Entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void InsertUser(User u);
    @Query("Select * from User where UserId=:UserId")
    User GetUser(int UserId);
    @Delete
    void DeleteUser(User user);
    @Query("Select * from User")
    List<User> GetAllUser();
    @Query("Select max(UserId) from User")
    int GetMaxUserId();
    @Update
    void UpdateUser(User u);
}
