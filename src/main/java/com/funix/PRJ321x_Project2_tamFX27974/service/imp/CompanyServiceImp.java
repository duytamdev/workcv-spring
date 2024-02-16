package com.funix.PRJ321x_Project2_tamFX27974.service.imp;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Company;
import com.funix.PRJ321x_Project2_tamFX27974.entity.CompanyWithRecruitmentCount;
import com.funix.PRJ321x_Project2_tamFX27974.repository.CompanyRepository;
import com.funix.PRJ321x_Project2_tamFX27974.service.CompanyService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService {

    private final CompanyRepository companyRepository;


    private final UserService userService;

    public CompanyServiceImp(CompanyRepository companyRepository, UserService userService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company update(Company companyUpdate) {
        Company company = companyRepository.findById(companyUpdate.getId()).orElse(null);
        if (company != null) {
            company.setEmail(companyUpdate.getEmail());
            company.setCompanyName(companyUpdate.getCompanyName());
            company.setAddress(companyUpdate.getAddress());
            company.setPhone(companyUpdate.getPhone());
            company.setDescription(companyUpdate.getDescription());
            company.setLogo(companyUpdate.getLogo());
            companyRepository.save(company);
            userService.updateUserAuthContext();
            return company;
        }
        return null;
    }

    @Override
    public Company save(Company company) {
        company.setUser(userService.getCurrentUser());
        Company newCompany = companyRepository.save(company);
        userService.updateCompanyById(userService.getCurrentUser().getId(), newCompany.getId());
        userService.updateUserAuthContext();
        return newCompany;
    }

    @Override
    @Transactional
    public void uploadLogo(int id, String logo) {
        companyRepository.setLogo(id, logo);
        userService.updateUserAuthContext();
    }

    @Override
    public List<CompanyWithRecruitmentCount> listTopCompaniesWithMostRecruitments() {
        Pageable pageable = PageRequest.of(0, 5);
        return companyRepository.findTopCompaniesWithMostRecruitments(pageable);
    }
}
