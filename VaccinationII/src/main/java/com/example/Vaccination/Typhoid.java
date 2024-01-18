package com.example.Vaccination;

import com.example.Vaccination.Vaccine;

public class Typhoid implements Vaccine {

    @Override
    public String getType(){
        return "Typhoid";
    }
}
