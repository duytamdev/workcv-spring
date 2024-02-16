package com.funix.PRJ321x_Project2_tamFX27974.service;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Cv;

public interface CvService {

    void save(Cv cv);

    Cv findByUserId(int id);

    void updateFileNameById(int id, String fileName);

    void deleteById(int id);

    boolean existsByUserId(int id);
}
