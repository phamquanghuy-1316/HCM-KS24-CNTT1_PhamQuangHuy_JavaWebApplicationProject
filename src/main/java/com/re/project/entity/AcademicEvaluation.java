package com.re.project.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "academic_evaluations")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AcademicEvaluation {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long id;

    @OneToOne
    @JoinColumn(name = "session_id")
    private MentoringSession session;

    @Column(columnDefinition = "TEXT")
    private String evaluation;

    private Integer score;

    private LocalDateTime createdAt;
}