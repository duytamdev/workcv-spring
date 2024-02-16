package com.funix.PRJ321x_Project2_tamFX27974.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_company")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "logo")
    private String logo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference
    private User user;

    private int recruitmentCount;

    @Override
    public String toString() {
        return "Company{id=" + id + ", companyName='" + companyName + "'}";
    }

    public String getLogoPath() {
        if (logo == null) return null;
        return "/assets/company-images/" + logo;
    }
}
