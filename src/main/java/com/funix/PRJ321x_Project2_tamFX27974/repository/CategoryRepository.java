package com.funix.PRJ321x_Project2_tamFX27974.repository;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // find top 4 categories
    List<Category> findTop4ByOrderByNumberChooseDesc();

    @Query("SELECT c.name, COUNT(r.id) FROM Category c LEFT JOIN Recruitment r ON r.category = c GROUP BY c.name ORDER BY COUNT(r.id) DESC")
    List<Object[]> findTopCategoriesWithOrWithoutRecruitments(Pageable pageable);

}
