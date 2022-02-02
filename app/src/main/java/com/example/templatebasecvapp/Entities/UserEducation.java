package com.example.templatebasecvapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEducation {
    @PrimaryKey(autoGenerate = true)
    private int UserEducationId;
    private String Institution;
    private String Description;
    private String FromYear;
    private String ToYear;
    private String Type;
    private double TotalMarks;
    private double ObtainedMarks;
    private double Percentage;
    private int UserId;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getTotalMarks() {
        return TotalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        TotalMarks = totalMarks;
    }

    public double getObtainedMarks() {
        return ObtainedMarks;
    }

    public void setObtainedMarks(double obtainedMarks) {
        ObtainedMarks = obtainedMarks;
    }

    public double getPercentage() {
        return Percentage;
    }

    public void setPercentage(double percentage) {
        Percentage = percentage;
    }

    public int getUserEducationId() {
        return UserEducationId;
    }

    public void setUserEducationId(int userEducationId) {
        UserEducationId = userEducationId;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public String getFromYear() {
        return FromYear;
    }

    public void setFromYear(String fromYear) {
        FromYear = fromYear;
    }

    public String getToYear() {
        return ToYear;
    }

    public void setToYear(String toYear) {
        ToYear = toYear;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
