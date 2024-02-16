package com.funix.PRJ321x_Project2_tamFX27974.repository;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepository extends JpaRepository<Cv, Integer> {
    Cv findByUserId(int id);

    @Modifying
    @Query(nativeQuery = true, value = "update cv c set c.file_name = :fileName where c.id = :id")
    void updateFileNameById(int id, String fileName);

    @Modifying
    @Query(nativeQuery = true, value = "update cv c set c.file_name = :fileName where c.user_id = :userId")
    void updateFileNameByUserId(int userId, String fileName);

    boolean existsByUserId(int userId);
}
