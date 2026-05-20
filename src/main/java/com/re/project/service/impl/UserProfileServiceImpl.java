package com.re.project.service.impl;

import com.re.project.entity.User;
import com.re.project.entity.UserProfile;

import com.re.project.repository.UserProfileRepository;

import com.re.project.service.UserProfileService;

import jakarta.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl
        implements UserProfileService {

    private final UserProfileRepository
            profileRepository;

    private final HttpSession session;

    @Override
    public UserProfile getCurrentProfile() {

        User user = (User)
                session.getAttribute(
                        "loggedInUser"
                );

        if(user == null) {

            throw new RuntimeException(
                    "User not logged in"
            );
        }

        return profileRepository
                .findByUser(user)
                .orElseGet(() -> {

                    UserProfile profile =
                            UserProfile.builder()
                                    .user(user)
                                    .build();

                    return profileRepository
                            .save(profile);
                });
    }

    @Override
    public void save(UserProfile profile) {

        User user = (User)
                session.getAttribute(
                        "loggedInUser"
                );

        if(user == null) {

            throw new RuntimeException(
                    "User not logged in"
            );
        }

        profile.setUser(user);

        profileRepository.save(profile);
    }
}