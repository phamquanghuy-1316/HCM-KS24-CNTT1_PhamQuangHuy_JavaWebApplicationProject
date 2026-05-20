package com.re.project.security;

import com.re.project.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor
        implements HandlerInterceptor {

    @Override
    public boolean preHandle(

            HttpServletRequest request,

            HttpServletResponse response,

            Object handler

    ) throws Exception {

        User user = (User)
                request.getSession()
                        .getAttribute(
                                "loggedInUser"
                        );

        String uri = request.getRequestURI();

        // NOT LOGIN

        if(user == null) {

            response.sendRedirect("/login");

            return false;
        }

        // ADMIN

        if(uri.startsWith("/admin")
                && !user.getRole()
                .name()
                .equals("ADMIN")) {

            response.sendError(403);

            return false;
        }

        // LECTURER

        if(uri.startsWith("/lecturer")
                && !user.getRole()
                .name()
                .equals("LECTURER")) {

            response.sendError(403);

            return false;
        }

        // STUDENT

        if(uri.startsWith("/student")
                && !user.getRole()
                .name()
                .equals("STUDENT")) {

            response.sendError(403);

            return false;
        }

        return true;
    }
}