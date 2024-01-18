package com.example.Vaccination;

import com.example.Vaccination.Vaccine;

public class Polio implements Vaccine {

    @Override
    public String getType(){
        return "Polio";
    }
}
