package com.funix.PRJ321x_Project2_tamFX27974.repository;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
