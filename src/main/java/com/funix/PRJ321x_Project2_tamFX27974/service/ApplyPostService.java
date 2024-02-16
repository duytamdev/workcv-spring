package com.funix.PRJ321x_Project2_tamFX27974.service;

import com.funix.PRJ321x_Project2_tamFX27974.entity.ApplyPost;

import java.util.List;

public interface ApplyPostService {

    boolean apply(String idRecruitment, String note);

    boolean applyWidthNewCv(String idRecruitment, String note, String fileName);

    void changeStatus(int id, String status);

    List<ApplyPost> findApplyPostsByCompanyId(int companyId);

    List<ApplyPost> findApplyPostsByUserId(int userId);

}
