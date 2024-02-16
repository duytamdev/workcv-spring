package com.funix.PRJ321x_Project2_tamFX27974.repository;

import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Modifying
    @Query(nativeQuery = true, value = "update user u set u.company_id = :companyId where u.id = :id")
    void updateCompanyById(int id, int companyId);

    //upload avt
    @Modifying
    @Query(nativeQuery = true, value = "update user u set u.image = :avatar where u.id = :id")
    void updateAvatarById(int id, String avatar);

    //upload cv
    @Modifying
    @Query(nativeQuery = true, value = "update user u set u.cv = :cv where u.id = :id")
    void updateCvById(int id, String cv);

    @Query("Select u from User u where u.fullName like %:name%")
    List<User> searchByNameLike(String name);
}
