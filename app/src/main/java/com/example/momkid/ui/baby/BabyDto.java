package com.example.momkid.ui.baby;


import java.io.Serializable;
import java.sql.Timestamp;

public class BabyDto implements Serializable {
    private int id;
    private String name;
    private String birthDate;
    private boolean isMale;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMale(boolean male) {
        isMale = male;
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
                "babyId=" + id +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDate + '\'' +
                ", isMale=" + isMale +
                '}';
    }
}
