package com.funix.PRJ321x_Project2_tamFX27974.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fav_recruitment")
@Data
public class FavRecruitment {

    @Id
    private int id;

    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "recruitment_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Recruitment recruitment;
}
