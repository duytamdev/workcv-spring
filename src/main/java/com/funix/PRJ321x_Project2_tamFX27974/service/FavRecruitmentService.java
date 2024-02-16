package com.funix.PRJ321x_Project2_tamFX27974.service;

import com.funix.PRJ321x_Project2_tamFX27974.entity.FavRecruitment;

public interface FavRecruitmentService {

    String fav(FavRecruitment favRecruitment);

    void unFav(int id);
}
