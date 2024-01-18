package com.example.Vaccination;

public interface User {
    Vaccine getVaccineDetails();
    void setUserDetails(String name, int age, TimeAndLocation timeAndLocation);
    void setAppointment();
    boolean IsVaccinated();
}
