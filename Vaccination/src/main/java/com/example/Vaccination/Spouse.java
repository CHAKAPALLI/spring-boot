package com.example.Vaccination;

public class Spouse implements User {

    private String name;
    private Integer age;
    private TimeAndLocation timeAndLocation;
    private Vaccine vaccine;
    private boolean isVaccinated = false;

    public Spouse( TimeAndLocation timeAndLocation, Vaccine vaccine){
        this.timeAndLocation = timeAndLocation;
        this.vaccine = vaccine;
    }

    @Override
    public void setAppointment() {
        this.isVaccinated = true;
        String vaccineDetails = vaccine.getType();
        System.out.println("Hello " + name + " your appointment has been fixed for " + vaccineDetails
                + " Vaccine on " + timeAndLocation.getDetails());
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
    public boolean IsVaccinated() {
        return this.isVaccinated;
    }
}
