package com.re.project.repository;

import com.re.project.entity.MentoringSession;

import com.re.project.entity.SessionStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

public interface MentoringSessionRepository
        extends JpaRepository<MentoringSession, Long> {

    boolean existsByLecturer_IdAndSessionDateAndSessionTime(

            Long lecturerId,

            LocalDate sessionDate,

            LocalTime sessionTime
    );

    boolean existsByStudent_IdAndSessionDateAndSessionTime(

            Long studentId,

            LocalDate sessionDate,

            LocalTime sessionTime
    );

    List<MentoringSession>
    findByStatus(
            SessionStatus status
    );
}