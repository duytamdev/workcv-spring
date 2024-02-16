package com.funix.PRJ321x_Project2_tamFX27974.controller;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Category;
import com.funix.PRJ321x_Project2_tamFX27974.entity.CompanyWithRecruitmentCount;
import com.funix.PRJ321x_Project2_tamFX27974.entity.Recruitment;
import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import com.funix.PRJ321x_Project2_tamFX27974.service.CategoryService;
import com.funix.PRJ321x_Project2_tamFX27974.service.CompanyService;
import com.funix.PRJ321x_Project2_tamFX27974.service.RecruitmentService;
import com.funix.PRJ321x_Project2_tamFX27974.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RecruitmentController {

    private final CompanyService companyService;
    private final RecruitmentService recruitmentService;
    private final CategoryService categoryService;

    @Autowired
    public RecruitmentController(CompanyService companyService, RecruitmentService recruitmentService, CategoryService categoryService) {
        this.companyService = companyService;
        this.recruitmentService = recruitmentService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(">>>>>>>>>>" + username);
        List<CompanyWithRecruitmentCount> companies = companyService.listTopCompaniesWithMostRecruitments();
        List<Recruitment> recruitments = recruitmentService.getAllRecruitments();
        List<Category> categories = categoryService.getTop4Categories();

        model.addAttribute("companies", companies);
        model.addAttribute("recruitments", recruitments);
        model.addAttribute("categories", categories);


        return "views/home";
    }

    @GetMapping("/list-post")
    public String postList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Recruitment> recruitments = recruitmentService.getRecruitmentsByUserId(user.getId());
        model.addAttribute("recruitments", recruitments);
        return "views/post-list";
    }

    @GetMapping("/recruitment/post")
    public String postDetail(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "views/post/create";
    }

    @PostMapping("/recruitment/add")
    public String addPost(@RequestParam("category_id") String categoryId,
                          @ModelAttribute("recruitment") Recruitment recruitment,
                          @RequestParam("deadlineS") String deadline,
                          RedirectAttributes redirectAttributes
    ) {
        recruitment.setDeadline(Utils.convertStringToDate(deadline));
        recruitmentService.saveRecruitment(recruitment, categoryId);
        redirectAttributes.addFlashAttribute("success", "true");
        return "redirect:/recruitment/post";
    }

    @GetMapping("/recruitment/edit/{id}")
    public String editPost(Model model, @PathVariable String id) {
        Recruitment recruitment = recruitmentService.getRecruitmentById(Integer.parseInt(id));
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("recruitment", recruitment);
        return "views/post/update";
    }

    @PostMapping("/recruitment/update")
    public String updatePost(@RequestParam("category_id") String categoryId,
                             @ModelAttribute("recruitment") Recruitment recruitment,
                             @RequestParam("deadlineS") String deadline) {
        recruitment.setDeadline(Utils.convertStringToDate(deadline));
        System.out.println(">>>>>>>>>>>>>>recruitment" + recruitment);
        recruitmentService.updateRecruitment(recruitment, categoryId);
        return "redirect:/recruitment/post";
    }

    @PostMapping("/recruitment/delete")
    public String deletePost(@RequestParam("id") String id) {
        recruitmentService.deleteRecruitment(Integer.parseInt(id));
        return "redirect:/list-post";
    }

    @GetMapping("/recruitment/detail/{id}")
    public String detailPost(Model model, @PathVariable String id) {
        System.out.println("id" + id);
        Recruitment recruitment = recruitmentService.getRecruitmentById(Integer.parseInt(id));
        model.addAttribute("recruitment", recruitment);
        System.out.println(">>>>>>>     >>>>>>>recruitment" + recruitment);
        return "views/post/detail";
    }
}
