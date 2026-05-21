package com.re.project.service.impl;

import com.re.project.dto.EvaluationRequest;

import com.re.project.entity.*;

import com.re.project.repository.*;

import com.re.project.service.EvaluationService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class EvaluationServiceImpl
        implements EvaluationService {

    private final MentoringSessionRepository
            sessionRepository;

    private final AcademicEvaluationRepository
            evaluationRepository;

    private final BorrowingRecordRepository
            recordRepository;

    private final BorrowingDetailRepository
            detailRepository;

    private final EquipmentRepository
            equipmentRepository;

    @Override
    @Transactional
    public void evaluate(
            EvaluationRequest request
    ) {

        // =========================
        // FIND SESSION
        // =========================

        MentoringSession session =

                sessionRepository
                        .findById(
                                request.getSessionId()
                        )
                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Session not found"
                                )
                        );

        // =========================
        // CHECK STATUS
        // =========================

        if(
                session.getStatus()
                        != SessionStatus.PENDING
        ) {

            throw new RuntimeException(
                    "Session already handled"
            );
        }

        // =========================
        // CREATE EVALUATION
        // =========================

        AcademicEvaluation evaluation =

                AcademicEvaluation.builder()

                        .session(session)

                        .evaluation(
                                request.getEvaluation()
                        )

                        .score(
                                request.getScore()
                        )

                        .createdAt(
                                LocalDateTime.now()
                        )

                        .build();

        evaluationRepository.save(
                evaluation
        );

        // =========================
        // CREATE BORROW RECORD
        // =========================

        BorrowingRecord record =

                BorrowingRecord.builder()

                        .session(session)

                        .student(
                                session.getStudent()
                        )

                        .status(
                                BorrowingStatus.PENDING
                        )

                        .createdAt(
                                LocalDateTime.now()
                        )

                        .build();

        recordRepository.save(record);

        // =========================
        // EQUIPMENT
        // =========================

        Equipment equipment =

                equipmentRepository
                        .findById(
                                request.getEquipmentId()
                        )
                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Equipment not found"
                                )
                        );

        // =========================
        // DETAIL
        // =========================

        BorrowingDetail detail =

                BorrowingDetail.builder()

                        .record(record)

                        .equipment(equipment)

                        .quantity(
                                request.getQuantity()
                        )

                        .build();

        detailRepository.save(detail);

        // =========================
        // UPDATE SESSION
        // =========================

        session.setStatus(
                SessionStatus.COMPLETED
        );

        sessionRepository.save(session);

        // =========================
        // TEST ROLLBACK
        // =========================

//        int x = 10 / 0;

    }
}