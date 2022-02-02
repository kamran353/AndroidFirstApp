package com.example.templatebasecvapp.Entities;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserHobby {
    @PrimaryKey(autoGenerate = true)
    private int HobbyId;
    private String Hobby;
    private int UserId;

    public int getHobbyId() {
        return HobbyId;
    }

    public void setHobbyId(int hobbyId) {
        HobbyId = hobbyId;
    }

    public String getHobby() {
        return Hobby;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
