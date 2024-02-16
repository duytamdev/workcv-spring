package com.funix.PRJ321x_Project2_tamFX27974.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cv")
public class Cv {

    @Column(name = "file_name")
    private String fileName;
    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Cv() {
    }

    public Cv(String fileName) {
        this.fileName = fileName;
    }

    public Cv(String fileName, User user) {
        this.fileName = fileName;
        this.user = user;
    }

    public String getPath() {
        return "assets/user-cvs/" + fileName;
    }
}