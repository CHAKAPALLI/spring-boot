package com.example.CarService.Controller;

import com.example.CarService.domain.Car;
import com.example.CarService.domain.Vehicle;
import com.example.CarService.service.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    Registration registration;

    @RequestMapping("/register")
    public String getRegistrationPage(Model carModel){
        Vehicle vehicle = registration.getNewCar();
        carModel.addAttribute("Vehicle", vehicle);
        return "carregister";
    }

    @RequestMapping("/done")
    public String getResponsePage(@ModelAttribute("vehicle") Car car){
        int registrationResult = registration.registerCar(car.getRegisterationNumber(), car.getCarName(), car.getCarDetails(), car.getCarWork());
        if(registrationResult == -1) {
            return "carregister";
        } else {
            return "success";
        }
    }

    @RequestMapping(value="/success")
    public String getSuccessPage(@RequestParam("id") String id) {
        // Logic to handle success page based on ID
        // For example:
        if (id.equals("1")) {
            return "success";
        } else {
            return "error"; // Handle other cases accordingly
        }
    }
}
