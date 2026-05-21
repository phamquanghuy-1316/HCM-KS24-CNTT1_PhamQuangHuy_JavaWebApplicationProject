package com.re.project.controller;

import com.re.project.entity.Lecturer;
import com.re.project.entity.User;
import com.re.project.entity.UserProfile;

import com.re.project.repository.LecturerRepository;
import com.re.project.repository.UserRepository;

import com.re.project.service.UserProfileService;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final LecturerRepository lecturerRepository;

    private final UserRepository userRepository;

    private final UserProfileService profileService;

    // =========================
    // LECTURER ACADEMIC PROFILE
    // =========================

    @GetMapping("/lecturer/academic-profile")
    public String lecturerAcademicProfile(

            Model model,

            HttpSession session

    ) {

        User user = (User)

                session.getAttribute(
                        "loggedInUser"
                );

        if(user == null) {

            return "redirect:/login";
        }

        Lecturer lecturer = lecturerRepository

                .findByUser(user)

                .orElseGet(() -> {

                    Lecturer newLecturer =

                            Lecturer.builder()

                                    .user(user)

                                    .specialization(
                                            "Not Updated"
                                    )

                                    .academicDegree(
                                            "Not Updated"
                                    )

                                    .experienceYears(0)

                                    .build();

                    return lecturerRepository
                            .save(newLecturer);
                });

        model.addAttribute(
                "lecturer",
                lecturer
        );

        return "lecturer/academic-profile";
    }

    @PostMapping("/lecturer/academic-profile")
    public String saveAcademicProfile(

            @ModelAttribute("lecturer")
            Lecturer lecturer

    ) {

        Lecturer oldLecturer =

                lecturerRepository

                        .findById(
                                lecturer.getId()
                        )

                        .orElseThrow();

        oldLecturer.setSpecialization(
                lecturer.getSpecialization()
        );

        oldLecturer.setAcademicDegree(
                lecturer.getAcademicDegree()
        );

        oldLecturer.setExperienceYears(
                lecturer.getExperienceYears()
        );

        lecturerRepository.save(
                oldLecturer
        );

        return "redirect:/lecturer/academic-profile?success";
    }

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