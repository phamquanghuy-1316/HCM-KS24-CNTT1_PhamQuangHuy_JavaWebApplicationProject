package com.re.project.service.impl;


import com.re.project.entity.User;
import com.re.project.entity.UserProfile;
import com.re.project.repository.UserProfileRepository;
import com.re.project.repository.UserRepository;
import com.re.project.service.UserProfileService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl
        implements UserProfileService {

    private final UserRepository userRepository;

    private final UserProfileRepository profileRepository;

    @Override
    public UserProfile getCurrentProfile() {

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = auth.getName();

        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        return profileRepository
                .findByUser(user)
                .orElse(
                        UserProfile.builder()
                                .user(user)
                                .build()
                );
    }

    @Override
    public void save(UserProfile profile) {

        profileRepository.save(profile);
    }
}
