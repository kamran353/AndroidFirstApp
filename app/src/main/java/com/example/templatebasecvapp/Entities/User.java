package com.example.templatebasecvapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private int UserId;
    private String UserPciture;
    private String UserName;
    private String FatherName;
    private String DateOfBirth;
    private String Gender;
    private String Country;
    private String Cnic;
    private String Email;
    private String Address;
    private String Nationality;
    private String Religion;
    private String Phone;
    private String Martial;
    private String Domicile;

    public String getUserPciture() {
        return UserPciture;
    }

    public void setUserPciture(String userPciture) {
        UserPciture = userPciture;
    }

    public String getCnic() {
        return Cnic;
    }

    public void setCnic(String cnic) {
        Cnic = cnic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getReligion() {
        return Religion;
    }

    public void setReligion(String religion) {
        Religion = religion;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMartial() {
        return Martial;
    }

    public void setMartial(String martial) {
        Martial = martial;
    }

    public String getDomicile() {
        return Domicile;
    }

    public void setDomicile(String domicile) {
        Domicile = domicile;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
