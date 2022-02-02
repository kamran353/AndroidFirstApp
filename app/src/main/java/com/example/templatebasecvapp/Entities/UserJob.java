package com.example.templatebasecvapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserJob {
    @PrimaryKey(autoGenerate = true)
    private int JobId;
    private String JobTitle;
    private String CompanyName;
    private String FromYear;
    private String ToYear;
    private String AdditionInformation;
    private int UserId;
    private String JobDescription;
    public int getJobId() {
        return JobId;
    }

    public String getJobDescription() {
        return JobDescription;
    }

    public void setJobDescription(String jobDescription) {
        JobDescription = jobDescription;
    }

    public void setJobId(int jobId) {
        JobId = jobId;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
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

    public String getAdditionInformation() {
        return AdditionInformation;
    }

    public void setAdditionInformation(String additionInformation) {
        AdditionInformation = additionInformation;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
