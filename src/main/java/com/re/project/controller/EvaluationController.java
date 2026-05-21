package com.re.project.controller;

import com.re.project.dto.EvaluationRequest;

import com.re.project.entity.Equipment;

import com.re.project.entity.MentoringSession;

import com.re.project.entity.SessionStatus;

import com.re.project.repository.EquipmentRepository;

import com.re.project.repository.MentoringSessionRepository;

import com.re.project.service.EvaluationService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class EvaluationController {

    private final MentoringSessionRepository
            sessionRepository;

    private final EquipmentRepository
            equipmentRepository;

    private final EvaluationService
            evaluationService;

    @GetMapping("/lecturer/evaluate")

    public String evaluatePage(
            Model model
    ) {

        List<MentoringSession> sessions =

                sessionRepository
                        .findByStatus(
                                SessionStatus.PENDING
                        );

        List<Equipment> equipments =

                equipmentRepository.findAll();

        model.addAttribute(
                "sessions",
                sessions
        );

        model.addAttribute(
                "equipments",
                equipments
        );

        model.addAttribute(
                "request",
                new EvaluationRequest()
        );

        return "lecturer/evaluate";
    }

    @PostMapping("/lecturer/evaluate")

    public String evaluate(

            @ModelAttribute("request")
            EvaluationRequest request,

            Model model

    ) {

        try {

            evaluationService.evaluate(
                    request
            );

            model.addAttribute(
                    "success",
                    "Evaluation success"
            );

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );
        }

        model.addAttribute(
                "sessions",

                sessionRepository.findByStatus(
                        SessionStatus.PENDING
                )
        );

        model.addAttribute(
                "equipments",
                equipmentRepository.findAll()
        );

        return "lecturer/evaluate";
    }
}