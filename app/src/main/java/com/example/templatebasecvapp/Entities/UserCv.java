package com.example.templatebasecvapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserCv {
    @PrimaryKey(autoGenerate = true)
    private int CvId;
    private int UserId;
    private String CreatedDate;
    public int getCvId() {
        return CvId;
    }

    public void setCvId(int cvId) {
        CvId = cvId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }


}
