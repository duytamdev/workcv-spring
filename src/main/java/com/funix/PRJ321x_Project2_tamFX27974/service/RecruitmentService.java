package com.funix.PRJ321x_Project2_tamFX27974.service;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Recruitment;

import java.util.List;

public interface RecruitmentService {

    List<Recruitment> getAllRecruitments();

    List<Recruitment> getRecruitmentsByUserId(int id);

    Recruitment getRecruitmentById(int id);

    void saveRecruitment(Recruitment recruitment, String categoryId);

    void updateRecruitment(Recruitment recruitmentUpdate, String categoryId);

    void deleteRecruitment(int id);

    List<Recruitment> searchByTitleLike(String title);

    List<Recruitment> searchByAddressLike(String address);

    List<Recruitment> findFavoriteRecruitmentsByUserId(int userId);
}
