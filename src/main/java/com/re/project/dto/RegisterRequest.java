package com.re.project.dto;

import com.re.project.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6,
            message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Please select role")
    private Role role;
}
