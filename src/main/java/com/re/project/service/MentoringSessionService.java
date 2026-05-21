package com.re.project.service;

import com.re.project.dto.BookingRequest;
import jakarta.servlet.http.HttpSession;

public interface MentoringSessionService {

    void bookSession(
            BookingRequest request,
            HttpSession session
    );
}
