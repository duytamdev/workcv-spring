package com.funix.PRJ321x_Project2_tamFX27974.controller;

import com.funix.PRJ321x_Project2_tamFX27974.entity.FavRecruitment;
import com.funix.PRJ321x_Project2_tamFX27974.entity.Recruitment;
import com.funix.PRJ321x_Project2_tamFX27974.entity.User;
import com.funix.PRJ321x_Project2_tamFX27974.service.FavRecruitmentService;
import com.funix.PRJ321x_Project2_tamFX27974.service.RecruitmentService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FavRecruitmentController {

    private final FavRecruitmentService favRecruitmentService;
    private final RecruitmentService recruitmentService;
    private final UserService userService;

    public FavRecruitmentController(FavRecruitmentService favRecruitmentService, RecruitmentService recruitmentService, UserService userService) {
        this.favRecruitmentService = favRecruitmentService;
        this.recruitmentService = recruitmentService;
        this.userService = userService;
    }

    @PostMapping("/like-recruitment")
    public ResponseEntity<String> likeRecruitment(@RequestParam("idRe") int recruitment) {
        System.out.println("like-recruitment---");
        User currentUser = userService.getCurrentUser();

        FavRecruitment favRecruitment = new FavRecruitment();
        favRecruitment.setRecruitment(new Recruitment(recruitment));
        favRecruitment.setUser(currentUser);

        return ResponseEntity.ok(favRecruitmentService.fav(favRecruitment));
    }

    @GetMapping("/save-job/get-list")
    public String getFavRecruitmentList(Model model) {
        List<Recruitment> favRecruitments = recruitmentService.findFavoriteRecruitmentsByUserId(userService.getCurrentUser().getId());
        model.addAttribute("saveJobList", favRecruitments);
        return "views/list-save-job";
    }

    @GetMapping("/save-job/delete/{id}")
    public String unlikeRecruitment(@PathVariable("id") int recruitment) {
        favRecruitmentService.unFav(recruitment);
        return "redirect:/save-job/get-list";
    }
}
