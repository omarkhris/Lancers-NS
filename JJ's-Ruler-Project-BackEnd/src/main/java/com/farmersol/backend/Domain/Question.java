package com.farmersol.backend.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;

    @ManyToOne
    private Questionnaire questionnaire;

    @OneToMany(mappedBy = "question")
    private List<Option> options;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
}
