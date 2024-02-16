package com.funix.PRJ321x_Project2_tamFX27974.repository;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {

    List<Recruitment> findRecruitmentsByUserId(int id);

    @Query(nativeQuery = true, value = "select * from recruitment r where r.id = :id")
    Recruitment findRecruitmentById(int id);

    @Query(nativeQuery = true, value = "select * from recruitment r where r.title like %:title%")
    List<Recruitment> searchByTitleLike(String title);

    //search like by address
    @Query(nativeQuery = true, value = "select * from recruitment r where r.address like %:address%")
    List<Recruitment> searchByAddressLike(String address);

    @Query("SELECT r FROM Recruitment r INNER JOIN FavRecruitment f ON r.id = f.recruitment.id WHERE f.user.id = :userId")
    List<Recruitment> findFavoriteRecruitmentsByUserId(@Param("userId") int userId);
}

