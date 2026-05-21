package com.re.project.controller;

import com.re.project.dto.BookingRequest;

import com.re.project.entity.Department;

import com.re.project.repository.DepartmentRepository;

import com.re.project.service.MentoringSessionService;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MentoringSessionController {

    private final MentoringSessionService
            mentoringSessionService;

    private final DepartmentRepository
            departmentRepository;

    @GetMapping("/student/book-session")
    public String bookSessionPage(
            Model model
    ) {

        List<Department> departments =
                departmentRepository.findAll();

        model.addAttribute(
                "departments",
                departments
        );

        model.addAttribute(
                "request",
                new BookingRequest()
        );

        return "student/book-session";
    }

    @PostMapping("/student/book-session")
    public String bookSession(

            @ModelAttribute("request")
            BookingRequest request,

            Model model,

            HttpSession session

    ) {

        try {

            mentoringSessionService.bookSession(
                    request,
                    session
            );

            model.addAttribute(
                    "success",
                    "Booking success"
            );

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );
        }

        List<Department> departments =
                departmentRepository.findAll();

        model.addAttribute(
                "departments",
                departments
        );

        return "student/book-session";
    }
}