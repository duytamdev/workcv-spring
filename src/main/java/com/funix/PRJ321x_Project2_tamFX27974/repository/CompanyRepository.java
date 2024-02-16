package com.funix.PRJ321x_Project2_tamFX27974.repository;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Company;
import com.funix.PRJ321x_Project2_tamFX27974.entity.CompanyWithRecruitmentCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Modifying
    @Query("update Company c set c.logo =:logo where c.id = :id")
    void setLogo(int id, String logo);

    @Query("SELECT new com.funix.PRJ321x_Project2_tamFX27974.entity.CompanyWithRecruitmentCount(c, COUNT(r)) " +
            "FROM Company c LEFT JOIN Recruitment r ON c.id = r.company.id AND r.deleted = false " +
            "GROUP BY c " +
            "ORDER BY COUNT(r) DESC")
    List<CompanyWithRecruitmentCount> findTopCompaniesWithMostRecruitments(Pageable pageable);

}
