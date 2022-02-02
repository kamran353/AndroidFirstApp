package com.example.templatebasecvapp.Mappings;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.templatebasecvapp.Entities.User;
import com.example.templatebasecvapp.Entities.UserCv;

public class UserAndCv {
    @Embedded
   public User user;
    @Relation(
            parentColumn = "UserId",
            entityColumn = "UserId"
    )
   public UserCv userCv;

}
