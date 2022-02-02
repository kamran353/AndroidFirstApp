package com.example.templatebasecvapp.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.templatebasecvapp.Entities.*;
import com.example.templatebasecvapp.Mappings.*;

import java.util.List;

@Dao
public interface UserCvDao {
    @Insert
    void InsertCv(UserCv cv);
    @Query("Select * from UserCv")
    List<UserCv> GetAllCvs();
    @Delete
    void DeleteCv(UserCv cv);
    @Query("Select * from UserCv cv inner join User u on u.UserId=cv.UserId")
    List<UserAndCv> GetAllCvsWithUser();

}
