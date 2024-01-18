package com.example.CarService.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class attendentController {

     
    @GetMapping("/attendent/{attendentId}")
    public String getAttendent(@PathVariable("attendentId") String attendentId, ModelMap modelMap) {     
        modelMap.addAttribute("attendentId", attendentId);
        return "attendent";
    }
}
