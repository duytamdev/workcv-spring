package com.funix.PRJ321x_Project2_tamFX27974.entity;

import lombok.Data;

@Data
public class CompanyWithRecruitmentCount {
    private Company company;
    private long recruitmentCount;

    public CompanyWithRecruitmentCount(Company company, long recruitmentCount) {
        this.company = company;
        this.recruitmentCount = recruitmentCount;
    }

    // Getters and setters
}
