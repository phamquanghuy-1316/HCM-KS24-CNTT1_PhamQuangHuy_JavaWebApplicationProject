package com.re.project.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrowing_records")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long id;

    @OneToOne
    @JoinColumn(name = "session_id")
    private MentoringSession session;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @Enumerated(EnumType.STRING)
    private BorrowingStatus status;

    private LocalDateTime createdAt;
}