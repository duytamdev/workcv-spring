package com.funix.PRJ321x_Project2_tamFX27974.entity;

import com.funix.PRJ321x_Project2_tamFX27974.utils.StatusApply;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "applypost")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyPost {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name_cv")
    private String nameCv;

    @Column(name = "status")
    private StatusApply status;

    @Column(name = "note")
    private String note;

    @Override
    public String toString() {
        return "ApplyPost{id=" + id + ", createdAt='" + createdAt + "'}";
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
