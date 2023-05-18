package com.example.momkid.ui.baby;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class BabyDto implements Serializable {
    private int babyId;
    private String name;
    private Date birthDay;
    private boolean isMale;

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setBabyId(int babyId) {
        this.babyId = babyId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMale(boolean male) {
        isMale = male;
    }

    public int getBabyId() {
        return babyId;
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
                "userId=" + babyId +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", isMale=" + isMale +
                '}';
    }
}
