package com.re.project.service.impl;

import com.re.project.dto.BookingRequest;

import com.re.project.entity.*;

import com.re.project.repository.*;

import com.re.project.service.MentoringSessionService;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class MentoringSessionServiceImpl
        implements MentoringSessionService {

    private final UserRepository
            userRepository;

    private final MentoringSessionRepository
            sessionRepository;

    @Override
    public void bookSession(

            BookingRequest request,

            HttpSession session

    ) {

        // =========================
        // CHECK NULL
        // =========================

        if(
                request.getSessionDate() == null
        ) {

            throw new RuntimeException(
                    "Please choose date"
            );
        }

        if(
                request.getSessionTime() == null
        ) {

            throw new RuntimeException(
                    "Please choose time"
            );
        }

        // =========================
        // CHECK PAST DATE/TIME
        // =========================

        LocalDate today = LocalDate.now();

        LocalTime now = LocalTime.now();

        if(
                request.getSessionDate()
                        .isBefore(today)
        ) {

            throw new RuntimeException(
                    "Cannot book in the past"
            );
        }

        if(
                request.getSessionDate()
                        .isEqual(today)

                        &&

                        request.getSessionTime()
                                .isBefore(now)
        ) {

            throw new RuntimeException(
                    "Cannot book past time"
            );
        }

        // =========================
        // GET CURRENT STUDENT
        // =========================

        User student = (User)

                session.getAttribute(
                        "loggedInUser"
                );

        if(student == null) {

            throw new RuntimeException(
                    "Please login again"
            );
        }

        // =========================
        // CHECK LECTURER CONFLICT
        // =========================

        boolean lecturerConflict =

                sessionRepository
                        .existsByLecturer_IdAndSessionDateAndSessionTime(

                                request.getLecturerId(),

                                request.getSessionDate(),

                                request.getSessionTime()
                        );

        if(lecturerConflict) {

            throw new RuntimeException(
                    "Lecturer already booked"
            );
        }

        // =========================
        // CHECK STUDENT CONFLICT
        // =========================

        boolean studentConflict =

                sessionRepository
                        .existsByStudent_IdAndSessionDateAndSessionTime(

                                student.getId(),

                                request.getSessionDate(),

                                request.getSessionTime()
                        );

        if(studentConflict) {

            throw new RuntimeException(
                    "You already have a session"
            );
        }

        // =========================
        // FIND LECTURER
        // =========================

        User lecturer =

                userRepository
                        .findById(
                                request.getLecturerId()
                        )
                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Lecturer not found"
                                )
                        );

        // =========================
        // CREATE SESSION
        // =========================

        MentoringSession mentoringSession =

                MentoringSession.builder()

                        .student(student)

                        .lecturer(lecturer)

                        .sessionDate(
                                request.getSessionDate()
                        )

                        .sessionTime(
                                request.getSessionTime()
                        )

                        .note(
                                request.getNote()
                        )

                        .status(
                                SessionStatus.PENDING
                        )

                        .createdAt(
                                LocalDateTime.now()
                        )

                        .build();

        sessionRepository.save(
                mentoringSession
        );
    }
}