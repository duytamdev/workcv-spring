package com.funix.PRJ321x_Project2_tamFX27974.service;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Company;
import com.funix.PRJ321x_Project2_tamFX27974.entity.CompanyWithRecruitmentCount;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company update(Company companyUpdate);

    Company save(Company company);

    void uploadLogo(int id, String logo);

    List<CompanyWithRecruitmentCount> listTopCompaniesWithMostRecruitments();
}
