package com.funix.PRJ321x_Project2_tamFX27974.controller;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Company;
import com.funix.PRJ321x_Project2_tamFX27974.entity.Cv;
import com.funix.PRJ321x_Project2_tamFX27974.entity.Role;
import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import com.funix.PRJ321x_Project2_tamFX27974.service.CvService;
import com.funix.PRJ321x_Project2_tamFX27974.service.RecruitmentService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    final
    RecruitmentService recruitmentService;
    private final UserService userService;
    private final CvService cvService;

    public AuthController(UserService userService, CvService cvService, RecruitmentService recruitmentService) {
        this.userService = userService;
        this.cvService = cvService;
        this.recruitmentService = recruitmentService;
    }

    @GetMapping("/auth/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "views/login";
    }

    @PostMapping("/auth/register")
    public String processRegistration(@ModelAttribute("user") User userDto, @RequestParam("role_id") int role_id, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("userDto: " + userDto.toString());
            System.out.println("role_id: " + role_id);
            userDto.setRole(new Role(role_id));
            userService.register(userDto);
            redirectAttributes.addFlashAttribute("registerSuccess", "ok");
            return "redirect:/auth/login";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("registerFail", "ok");
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user.getRole().getId() == 2) {
            Company company = user.getCompany();
            model.addAttribute("companyInformation", company);
        }
        if (user.getRole().getId() == 1) {
            Cv cv = cvService.findByUserId(user.getId());
            model.addAttribute("Cv", cv);
        }

        model.addAttribute("userInformation", user);
        return "views/profile";
    }

    @PostMapping("/user/update-profile")
    public String updateProfile(@ModelAttribute("user") User userUpdate, Model model) {
        System.out.println(">>>>>>>u" + userUpdate);
        userService.update(userUpdate);
        return "redirect:/profile";
    }

}
