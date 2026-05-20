package com.re.project.controller;

import com.re.project.entity.UserProfile;

import com.re.project.service.UserProfileService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserProfileService
            profileService;

    // =========================
    // STUDENT PROFILE
    // =========================

    @GetMapping("/student/profile")
    public String studentProfile(Model model) {

        model.addAttribute(
                "profile",
                profileService.getCurrentProfile()
        );

        return "student/profile";
    }

    @PostMapping("/student/profile")
    public String saveStudentProfile(

            @ModelAttribute("profile")
            UserProfile profile

    ) {

        profileService.save(profile);

        return "redirect:/student/profile?success";
    }

    // =========================
    // LECTURER PROFILE
    // =========================

    @GetMapping("/lecturer/profile")
    public String lecturerProfile(Model model) {

        model.addAttribute(
                "profile",
                profileService.getCurrentProfile()
        );

        return "lecturer/profile";
    }

    @PostMapping("/lecturer/profile")
    public String saveLecturerProfile(

            @ModelAttribute("profile")
            UserProfile profile

    ) {

        profileService.save(profile);

        return "redirect:/lecturer/profile?success";
    }

    // =========================
    // ADMIN PROFILE
    // =========================

    @GetMapping("/admin/profile")
    public String adminProfile(Model model) {

        model.addAttribute(
                "profile",
                profileService.getCurrentProfile()
        );

        return "admin/profile";
    }

    @PostMapping("/admin/profile")
    public String saveAdminProfile(

            @ModelAttribute("profile")
            UserProfile profile

    ) {

        profileService.save(profile);

        return "redirect:/admin/profile?success";
    }
}