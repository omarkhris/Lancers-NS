package com.farmersol.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Farmer_User farmerUser;


    @ManyToOne
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;


    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Response> responseList;


    private int score;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status;



    public Quiz(Farmer_User farmerUser, Questionnaire questionnaire, LocalDateTime startTime, String status) {
        this.farmerUser = farmerUser;
        this.questionnaire = questionnaire;
        this.score = 0;
        this.startTime = startTime;
        this.status = status;
    }
}
