package com.example.CarService.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SupportController {

    
    @RequestMapping(value = "/support")
    public String getSupportPage(ModelMap modelMap, String id) {
        modelMap.addAttribute("id", id);
        return "support";
    }
}
