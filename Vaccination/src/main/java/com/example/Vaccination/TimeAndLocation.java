package com.example.Vaccination;


public class TimeAndLocation {
    private String timeSlot;
    private String location;
    private String date;

    public TimeAndLocation() {}

    public TimeAndLocation(String timeSlot, String location, String date){
        this.timeSlot = timeSlot;
        this.location = location;
        this.date = date;
    }

    public void setDetails(String timeSlot, String location, String date){
        this.timeSlot = timeSlot;
        this.location = location;
        this.date = date;
    }

    public String getDetails() {
        return this.timeSlot + " at " + this.location + " on " + this.date;
    }
}
