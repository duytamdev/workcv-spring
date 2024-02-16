package com.funix.PRJ321x_Project2_tamFX27974.controller;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Recruitment;
import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import com.funix.PRJ321x_Project2_tamFX27974.service.RecruitmentService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    final
    RecruitmentService recruitmentService;
    final
    UserService userService;

    public SearchController(RecruitmentService recruitmentService, UserService userService) {
        this.recruitmentService = recruitmentService;
        this.userService = userService;
    }

    @GetMapping("/recruitment")
    public String searchRecruitment(@Param("q") String q, Model model) {
        List<Recruitment> recruitments = new ArrayList<>();
        if (q != null && !q.isEmpty()) {
            recruitments = recruitmentService.searchByTitleLike(q);
        }
        model.addAttribute("keySearch", q);
        model.addAttribute("recruitments", recruitments);
        return "views/search/recruitment";
    }

    @GetMapping("/user")
    public String searchUser(@Param("q") String q, Model model) {
        List<User> users = new ArrayList<>();
        if (q != null && !q.isEmpty()) {
            users = userService.searchByNameLike(q);
        }
        model.addAttribute("users", users);
        model.addAttribute("keySearch", q);
        return "views/search/user";
    }

    @GetMapping("/address")
    public String searchAddress(@Param("q") String q, Model model) {
        List<Recruitment> recruitments = new ArrayList<>();
        if (q != null && !q.isEmpty()) {
            recruitments = recruitmentService.searchByAddressLike(q);
        }
        model.addAttribute("keySearch", q);
        model.addAttribute("recruitments", recruitments);
        return "views/search/address";
    }
}
