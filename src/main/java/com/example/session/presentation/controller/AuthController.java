package com.example.session.presentation.controller;

import com.example.session.app.dto.UserSignInDto;
import com.example.session.app.dto.UserSignUpDto;
import com.example.session.core.domain.service.ISessionService;
import com.example.session.core.domain.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private final IUserService userService;
    private final ISessionService sessionService;

    public AuthController(IUserService userService, ISessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("UserSignInDto", new UserSignInDto());
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserSignUpDto());
        return "signup";
    }

    @PostMapping("/user-signup")
    public String signup(@Valid @ModelAttribute("userDto") UserSignUpDto userDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        try {
            if (userDto.getNames().isBlank()) {
                bindingResult.addError(new FieldError("userDto", "names", ""));
            }
            if (userDto.getEmail().isBlank()) {
                bindingResult.addError(new FieldError("userDto", "email", ""));
            }
            if (userDto.getPassword().isBlank()) {
                bindingResult.addError(new FieldError("userDto", "password", ""));
            }
            if (bindingResult.hasErrors()) {
                model.addAttribute("errorLogin", false);
                return "signup";
            }
            try {
                userService.create(userDto);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorLogin", true);
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
                return "redirect:signup";
            }
            return "redirect:/?success";
        } catch (Exception e) {
            model.addAttribute("errorLogin", true);
            model.addAttribute("errorMessage", "Se ha presentado un error al intentar registrar.");
            return "signup";
        }
    }
}
