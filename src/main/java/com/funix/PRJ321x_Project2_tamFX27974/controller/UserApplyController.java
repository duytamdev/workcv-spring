package com.funix.PRJ321x_Project2_tamFX27974.controller;

import com.funix.PRJ321x_Project2_tamFX27974.entity.ApplyPost;
import com.funix.PRJ321x_Project2_tamFX27974.service.ApplyPostService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserApplyController {

    final
    ApplyPostService applyPostService;

    final
    UserService userService;

    public UserApplyController(ApplyPostService applyPostService, UserService userService) {
        this.applyPostService = applyPostService;
        this.userService = userService;
    }

    @GetMapping("/users-apply")
    public String usersApply(Model model) {
        List<ApplyPost> applyPosts = applyPostService.findApplyPostsByCompanyId(userService.getCurrentUser().getCompany().getId());

        model.addAttribute("applyPosts", applyPosts);
        return "/views/list-user";
    }

    @GetMapping("/user/get-list-apply")
    public String getListApply(Model model) {
        List<ApplyPost> saveJobList = applyPostService.findApplyPostsByUserId(userService.getCurrentUser().getId());
        model.addAttribute("saveJobList", saveJobList);
        return "/views/list-apply-job";
    }
}
