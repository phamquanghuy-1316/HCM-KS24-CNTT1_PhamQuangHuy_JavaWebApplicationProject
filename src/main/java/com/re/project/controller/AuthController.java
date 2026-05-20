package com.re.project.controller;


import com.re.project.dto.LoginRequest;
import com.re.project.dto.RegisterRequest;
import com.re.project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model) {

        model.addAttribute("loginRequest",
                new LoginRequest());

        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        model.addAttribute("request",
                new RegisterRequest());

        return "register";
    }

    @PostMapping("/register")
    public String register(

            @Valid
            @ModelAttribute("request")
            RegisterRequest request,

            BindingResult result,

            Model model

    ) {

        // VALIDATION ERROR

        if(result.hasErrors()) {

            System.out.println(result.getAllErrors());

            return "register";
        }

        try {

            userService.register(request);

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "register";
        }

        return "redirect:/login";
    }

    @PostMapping("/do-login")
    public String doLogin(

            @Valid
            @ModelAttribute("loginRequest")
            LoginRequest request,

            BindingResult result,

            Model model,

            jakarta.servlet.http.HttpSession session

    ) {

        if(result.hasErrors()) {
            return "login";
        }

        try {

            var user = userService.login(request);

            // SAVE SESSION

            session.setAttribute(
                    "loggedInUser",
                    user
            );

            // ROLE REDIRECT

            if(user.getRole().name().equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            }

            if(user.getRole().name().equals("LECTURER")) {
                return "redirect:/lecturer/dashboard";
            }

            return "redirect:/student/dashboard";

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "loginRequest",
                    request
            );

            return "login";
        }
    }
}
