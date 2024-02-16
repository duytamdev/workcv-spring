package com.funix.PRJ321x_Project2_tamFX27974.repository;

import com.funix.PRJ321x_Project2_tamFX27974.entity.FavRecruitment;
import com.funix.PRJ321x_Project2_tamFX27974.entity.Recruitment;
import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavRecruitmentRepository extends JpaRepository<FavRecruitment, Integer> {

    boolean existsByUserAndRecruitment(User user, Recruitment recruitment);
    // un fav with user id and recruitment id

    @Modifying
    @Transactional
    @Query("delete from FavRecruitment f where f.user.id = :userId and f.recruitment.id = :recruitmentId")
    void deleteByUserAndRecruitment(@Param("userId") int userId, @Param("recruitmentId") int recruitmentId);
}
