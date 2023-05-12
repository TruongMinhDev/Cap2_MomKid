package com.example.momkid.ui.baby;


import java.sql.Timestamp;
import java.util.Date;

public class BabyDto {
    private int userId;
    private String name;
    private Date birthDay;
    private boolean isMale;

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMale(boolean male) {
        isMale = male;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }


    public boolean isMale() {
        return isMale;
    }

    @Override
    public String toString() {
        return "BabyDto{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", isMale=" + isMale +
                '}';
    }
}
