package com.example.Vaccination;

import com.example.Vaccination.User;
import com.example.Vaccination.Vaccine;
import com.example.Vaccination.TimeAndLocation;

public class Father implements User {

    private String name;
    private Integer age;
    private TimeAndLocation timeAndLocation;
    private Vaccine vaccine;
    private boolean isVaccinated = false;

    Father() {}

    public Father(TimeAndLocation timeAndLocation, Vaccine vaccine){
        this.timeAndLocation = timeAndLocation;
        this.vaccine = vaccine;
    }

    @Override
    public Vaccine getVaccineDetails() {
        return this.vaccine;
    }

    @Override
    public void setUserDetails(String name, int age, TimeAndLocation timeAndLocation) {
        this.name = name;
        this.age = age;
        this.timeAndLocation = timeAndLocation;
    }

    @Override
    public void setAppointment() {
        this.isVaccinated = true;
        String vaccineType = vaccine.getType();
        System.out.println("Hello " + name + " your appointment has been fixed for " + vaccineType
                + " Vaccine on " + timeAndLocation.getDetails());
    }

    @Override
    public boolean IsVaccinated() {
        return this.isVaccinated;
    }
}
