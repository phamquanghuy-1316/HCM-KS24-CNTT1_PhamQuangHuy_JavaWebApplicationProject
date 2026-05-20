package com.re.project.controller;

import com.re.project.entity.User;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // STUDENT

    @GetMapping("/student/dashboard")
    public String studentDashboard(
            HttpSession session
    ) {

        User user = (User)
                session.getAttribute(
                        "loggedInUser"
                );

        if(user == null) {
            return "redirect:/login";
        }

        if(!user.getRole().name()
                .equals("STUDENT")) {

            return "redirect:/login";
        }

        return "student/dashboard";
    }

    // LECTURER

    @GetMapping("/lecturer/dashboard")
    public String lecturerDashboard(
            HttpSession session
    ) {

        User user = (User)
                session.getAttribute(
                        "loggedInUser"
                );

        if(user == null) {
            return "redirect:/login";
        }

        if(!user.getRole().name()
                .equals("LECTURER")) {

            return "redirect:/login";
        }

        return "lecturer/dashboard";
    }

    // ADMIN

    @GetMapping("/admin/dashboard")
    public String adminDashboard(
            HttpSession session
    ) {

        User user = (User)
                session.getAttribute(
                        "loggedInUser"
                );

        if(user == null) {
            return "redirect:/login";
        }

        if(!user.getRole().name()
                .equals("ADMIN")) {

            return "redirect:/login";
        }

        return "admin/dashboard";
    }
}