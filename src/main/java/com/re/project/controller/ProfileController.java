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

    private final UserProfileService profileService;

    // SHOW PROFILE
    @GetMapping("/student/profile")
    public String studentProfile(Model model) {

        UserProfile profile =
                profileService.getCurrentProfile();

        model.addAttribute(
                "profile",
                profile
        );

        return "student/profile";
    }

    // SAVE PROFILE
    @PostMapping("/student/profile")
    public String saveProfile(

            @ModelAttribute("profile")
            UserProfile profile

    ) {

        profileService.save(profile);

        return "redirect:/student/profile";
    }
}
