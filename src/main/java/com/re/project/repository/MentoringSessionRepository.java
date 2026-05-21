package com.re.project.repository;

import com.re.project.entity.MentoringSession;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface MentoringSessionRepository
        extends JpaRepository<MentoringSession, Long> {

    // CHECK LECTURER CONFLICT

    boolean existsByLecturer_IdAndSessionDateAndSessionTime(

            Long lecturerId,

            LocalDate sessionDate,

            LocalTime sessionTime
    );

    // CHECK STUDENT CONFLICT

    boolean existsByStudent_IdAndSessionDateAndSessionTime(

            Long studentId,

            LocalDate sessionDate,

            LocalTime sessionTime
    );
}