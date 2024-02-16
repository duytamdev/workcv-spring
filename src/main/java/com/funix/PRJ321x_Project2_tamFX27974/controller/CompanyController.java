package com.funix.PRJ321x_Project2_tamFX27974.controller;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Company;
import com.funix.PRJ321x_Project2_tamFX27974.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PutMapping("/company/update")
    public String updateCompany(@ModelAttribute("company") Company companyUpdate) {
        System.out.println(">>>>>>>company update: " + companyUpdate);
        companyService.update(companyUpdate);
        return "redirect:/profile";
    }

    @PostMapping("/company/save")
    public String saveCompany(@ModelAttribute("company") Company company) {
        System.out.println(">>>>>>>company save: " + company);
        companyService.save(company);
        return "redirect:/profile";
    }
}
