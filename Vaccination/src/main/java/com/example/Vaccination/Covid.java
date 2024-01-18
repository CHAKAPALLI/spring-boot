package com.example.Vaccination;

import com.example.Vaccination.Vaccine;

public class Covid implements Vaccine {

    @Override
    public String getType(){
        return "Covid";
    }

}
