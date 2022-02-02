package com.example.templatebasecvapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserSkill {
    @PrimaryKey(autoGenerate = true)
    private int SkillId;
    private String Skill;
    private int UserId;

    public int getSkillId() {
        return SkillId;
    }

    public void setSkillId(int skillId) {
        SkillId = skillId;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
