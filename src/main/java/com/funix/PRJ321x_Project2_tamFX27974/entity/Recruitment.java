package com.funix.PRJ321x_Project2_tamFX27974.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;

@Entity
@Table(name = "recruitment")
@Data
@SQLDelete(sql = "UPDATE recruitment SET deleted = true WHERE id=?")
@SQLRestriction(value = "deleted = false")
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "experience")
    private String experience;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "salary")
    private String salary;

    @Column(name = "status")
    private int status;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "view")
    private int view;

    @Column(name = "deadline")
    @Temporal(TemporalType.DATE)
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    @Column(name = "deleted")
    private boolean deleted;

    public Recruitment() {
    }

    public Recruitment(int id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();  // Tự động set ngày hiện tại khi đối tượng được persist
    }
}
