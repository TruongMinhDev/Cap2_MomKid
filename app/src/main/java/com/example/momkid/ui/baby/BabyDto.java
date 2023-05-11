package com.example.momkid.ui.baby;


public class BabyDto {
    private int userId;
    private String name;
    private String birthDay;
    private boolean isMale;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
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

    public String getBirthDay() {
        return birthDay;
    }

    public boolean isMale() {
        return isMale;
    }
}
