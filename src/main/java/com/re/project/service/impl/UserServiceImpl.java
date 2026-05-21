package com.re.project.service.impl;

import com.re.project.dto.LoginRequest;
import com.re.project.dto.RegisterRequest;
import com.re.project.entity.User;
import com.re.project.repository.LecturerRepository;
import com.re.project.repository.UserRepository;
import com.re.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.re.project.entity.Lecturer;
import com.re.project.entity.Role;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LecturerRepository lecturerRepository;

    @Override
    public void register(RegisterRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);

        // AUTO CREATE LECTURER
        if(user.getRole() == Role.LECTURER) {

            Lecturer lecturer = Lecturer.builder()

                    .user(user)

                    .specialization(
                            "Not Updated"
                    )

                    .build();

            lecturerRepository.save(lecturer);
        }
    }

    @Override
    public User login(LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Username not found"));

        // DEBUG

        System.out.println("INPUT PASSWORD: "
                + request.getPassword());

        System.out.println("DB PASSWORD: "
                + user.getPassword());

        boolean checkPassword =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        System.out.println("MATCH: "
                + checkPassword);

        if(!checkPassword) {

            throw new RuntimeException(
                    "Wrong password"
            );
        }

        return user;
    }
}
