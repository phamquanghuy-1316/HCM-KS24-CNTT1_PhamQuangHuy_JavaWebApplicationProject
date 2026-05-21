package com.re.project.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "mentoring_sessions")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MentoringSession {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private User lecturer;

    private LocalDate sessionDate;

    private LocalTime sessionTime;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    @Column(columnDefinition = "TEXT")
    private String note;

    private LocalDateTime createdAt;
}