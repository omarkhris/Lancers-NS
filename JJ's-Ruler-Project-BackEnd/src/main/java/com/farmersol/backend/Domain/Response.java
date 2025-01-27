package com.farmersol.backend.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz; // Reference to the question being answered
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question; // Reference to the question being answered


    @ManyToOne
    @JoinColumn(name = "option_id", referencedColumnName = "id")
    private Option selectedOption; //The option selected by the user (for MCQs)

    @Column(name = "answer_text", columnDefinition = "TEXT",   nullable = false)
    private String answerText; // The user's answer (for open-ended questions)



}
