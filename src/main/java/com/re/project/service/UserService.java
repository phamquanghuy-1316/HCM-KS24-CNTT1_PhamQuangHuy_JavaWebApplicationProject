package com.re.project.service;

import com.re.project.dto.LoginRequest;
import com.re.project.dto.RegisterRequest;
import com.re.project.entity.User;

public interface UserService {

    void register(RegisterRequest request);

    User login(LoginRequest request);
}
