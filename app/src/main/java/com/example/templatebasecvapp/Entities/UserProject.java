package com.example.templatebasecvapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserProject {
    @PrimaryKey
    private int ProjectId;
    private String ProjectTitle;
    private String OrganizationName;
    private String FromYear;
    private String ToYear;
    private String AdditionInformation;
    private int UserId;

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        ProjectTitle = projectTitle;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
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
