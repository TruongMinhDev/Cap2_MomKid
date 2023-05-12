package com.example.momkid.ui.schedule;



public class ScheduleDto {

    private String timeSchedule;
    private String contentSchedule;

    public ScheduleDto(String timeSchedule, String contentSchedule) {
        this.timeSchedule = timeSchedule;
        this.contentSchedule = contentSchedule;
    }

    public String getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(String timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    public String getContentSchedule() {
        return contentSchedule;
    }

    public void setContentSchedule(String contentSchedule) {
        this.contentSchedule = contentSchedule;
    }
}