package com.funix.PRJ321x_Project2_tamFX27974.repository;

import com.funix.PRJ321x_Project2_tamFX27974.entity.ApplyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyPostRepository extends JpaRepository<ApplyPost, Integer> {

    @Modifying
    @Query("update ApplyPost a set a.status = :status where a.id = :id")
    void changeStatus(int id, String status);

    boolean existsByRecruitmentIdAndUserId(int recruitmentId, int userId);

    @Query("SELECT ap FROM ApplyPost ap JOIN ap.recruitment r WHERE r.company.id = :companyId")
    List<ApplyPost> findApplyPostsByCompanyId(@Param("companyId") int companyId);

    // find all apply posts by user id
    @Query("SELECT ap FROM ApplyPost ap WHERE ap.user.id = :userId")
    List<ApplyPost> findApplyPostsByUserId(@Param("userId") int userId);


}
