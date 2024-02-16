package com.funix.PRJ321x_Project2_tamFX27974.service.imp;

import com.funix.PRJ321x_Project2_tamFX27974.entity.ApplyPost;
import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import com.funix.PRJ321x_Project2_tamFX27974.repository.ApplyPostRepository;
import com.funix.PRJ321x_Project2_tamFX27974.service.ApplyPostService;
import com.funix.PRJ321x_Project2_tamFX27974.service.CvService;
import com.funix.PRJ321x_Project2_tamFX27974.service.RecruitmentService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import com.funix.PRJ321x_Project2_tamFX27974.utils.StatusApply;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyPostServiceImp implements ApplyPostService {

    final
    UserService userService;
    final
    RecruitmentService recruitmentService;
    final
    CvService cvService;
    private final ApplyPostRepository applyPostRepository;

    public ApplyPostServiceImp(ApplyPostRepository applyPostRepository, UserService userService, RecruitmentService recruitmentService, CvService cvService) {
        this.applyPostRepository = applyPostRepository;
        this.userService = userService;
        this.recruitmentService = recruitmentService;
        this.cvService = cvService;
    }

    @Override
    public boolean apply(String idRecruitment, String note) {

        User user = userService.getCurrentUser();

        // check if user has already applied for this recruitment
        if (applyPostRepository.existsByRecruitmentIdAndUserId(Integer.parseInt(idRecruitment), user.getId())) {
            return false;
        } else {
            try {
                ApplyPost applyPost = new ApplyPost();
                applyPost.setRecruitment(recruitmentService.getRecruitmentById(Integer.parseInt(idRecruitment)));
                applyPost.setUser(user);
                applyPost.setNote(note);
                applyPost.setNameCv(cvService.findByUserId(user.getId()).getFileName());
                applyPost.setStatus(StatusApply.NEW);
                applyPostRepository.save(applyPost);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean applyWidthNewCv(String idRecruitment, String note, String fileName) {

        User user = userService.getCurrentUser();

        // check if user has already applied for this recruitment
        if (applyPostRepository.existsByRecruitmentIdAndUserId(Integer.parseInt(idRecruitment), user.getId())) {
            return false;
        } else {
            try {
                ApplyPost applyPost = new ApplyPost();
                applyPost.setRecruitment(recruitmentService.getRecruitmentById(Integer.parseInt(idRecruitment)));
                applyPost.setUser(user);
                applyPost.setNote(note);
                applyPost.setNameCv(fileName);
                applyPost.setStatus(StatusApply.NEW);
                applyPostRepository.save(applyPost);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public void changeStatus(int id, String status) {
        applyPostRepository.changeStatus(id, status);
    }

    @Override
    public List<ApplyPost> findApplyPostsByCompanyId(int companyId) {
        return applyPostRepository.findApplyPostsByCompanyId(companyId);
    }

    @Override
    public List<ApplyPost> findApplyPostsByUserId(int userId) {
        return applyPostRepository.findApplyPostsByUserId(userId);
    }
}
