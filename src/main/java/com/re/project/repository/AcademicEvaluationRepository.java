package com.re.project.repository;

import com.re.project.entity.AcademicEvaluation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicEvaluationRepository
        extends JpaRepository<AcademicEvaluation, Long> {
}