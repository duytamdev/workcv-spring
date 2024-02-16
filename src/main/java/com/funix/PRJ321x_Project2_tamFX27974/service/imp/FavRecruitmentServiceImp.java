package com.funix.PRJ321x_Project2_tamFX27974.service.imp;

import com.funix.PRJ321x_Project2_tamFX27974.entity.FavRecruitment;
import com.funix.PRJ321x_Project2_tamFX27974.repository.FavRecruitmentRepository;
import com.funix.PRJ321x_Project2_tamFX27974.service.FavRecruitmentService;
import com.funix.PRJ321x_Project2_tamFX27974.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class FavRecruitmentServiceImp implements FavRecruitmentService {

    private final FavRecruitmentRepository favRecruitmentRepository;
    private final UserService userService;

    public FavRecruitmentServiceImp(FavRecruitmentRepository favRecruitmentRepository, UserService userService) {
        this.favRecruitmentRepository = favRecruitmentRepository;
        this.userService = userService;
    }

    @Override
    public String fav(FavRecruitment favRecruitment) {
        // check user has liked this recruitment
        if (favRecruitmentRepository.existsByUserAndRecruitment(favRecruitment.getUser(), favRecruitment.getRecruitment())) {
            return "liked";
        }
        favRecruitmentRepository.save(favRecruitment);
        return "ok";
    }

    @Override
    public void unFav(int recruitmentId) {
        int userId = userService.getCurrentUser().getId();
        favRecruitmentRepository.deleteByUserAndRecruitment(userId, recruitmentId);
    }
}
