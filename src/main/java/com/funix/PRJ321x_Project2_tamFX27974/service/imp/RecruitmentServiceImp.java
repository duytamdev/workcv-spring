package com.funix.PRJ321x_Project2_tamFX27974.service.imp;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Category;
import com.funix.PRJ321x_Project2_tamFX27974.entity.Recruitment;
import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import com.funix.PRJ321x_Project2_tamFX27974.repository.RecruitmentRepository;
import com.funix.PRJ321x_Project2_tamFX27974.service.CategoryService;
import com.funix.PRJ321x_Project2_tamFX27974.service.RecruitmentService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentServiceImp implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    public RecruitmentServiceImp(RecruitmentRepository recruitmentRepository, CategoryService categoryService, UserService userService) {
        this.recruitmentRepository = recruitmentRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public List<Recruitment> getAllRecruitments() {
        return recruitmentRepository.findAll();
    }

    @Override
    public List<Recruitment> getRecruitmentsByUserId(int id) {
        return recruitmentRepository.findRecruitmentsByUserId(id);
    }

    @Override
    public Recruitment getRecruitmentById(int id) {
        return recruitmentRepository.findRecruitmentById(id);
    }

    @Override
    public void saveRecruitment(Recruitment recruitment, String categoryId) {
        Category category = categoryService.get(Integer.parseInt(categoryId));
        User user = userService.getCurrentUser();
        recruitment.setUser(user);
        recruitment.setCompany(user.getCompany());
        recruitment.setCategory(category);
        recruitmentRepository.save(recruitment);
    }

    @Override
    public void updateRecruitment(Recruitment recruitmentUpdate, String categoryId) {
        Recruitment recruitment = recruitmentRepository.findRecruitmentById(recruitmentUpdate.getId());
        Category category = categoryService.get(Integer.parseInt(categoryId));
        recruitment.setCategory(category);

        recruitment.setTitle(recruitmentUpdate.getTitle());
        recruitment.setDescription(recruitmentUpdate.getDescription());
        recruitment.setExperience(recruitmentUpdate.getExperience());
        recruitment.setQuantity(recruitmentUpdate.getQuantity());
        recruitment.setAddress(recruitmentUpdate.getAddress());
        recruitment.setDeadline(recruitmentUpdate.getDeadline());
        recruitment.setSalary(recruitmentUpdate.getSalary());
        recruitment.setType(recruitmentUpdate.getType());
        recruitmentRepository.save(recruitment);
    }

    @Override
    public void deleteRecruitment(int id) {
        recruitmentRepository.deleteById(id);
    }

    @Override
    public List<Recruitment> searchByTitleLike(String title) {
        return recruitmentRepository.searchByTitleLike(title);
    }

    @Override
    public List<Recruitment> searchByAddressLike(String address) {
        return recruitmentRepository.searchByAddressLike(address);
    }

    @Override
    public List<Recruitment> findFavoriteRecruitmentsByUserId(int userId) {
        return recruitmentRepository.findFavoriteRecruitmentsByUserId(userId);
    }
}
