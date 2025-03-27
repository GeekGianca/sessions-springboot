package com.example.session.presentation.controller;

import com.example.session.app.mapper.SessionMapper;
import com.example.session.core.domain.service.ISessionService;
import com.example.session.core.model.SessionEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ISessionService sessionService;

    public HomeController(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        List<SessionEntity> sessions = sessionService.getSessions();
        model.addAttribute("user", userDetails.getUsername());
        model.addAttribute("sessions", sessions.stream().map(SessionMapper::sessionToSessionDto).toList());
        return "home";
    }

    @GetMapping("/show-session")
    public String sessionDetail(Model model) {
        return "detail";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            sessionService.logout(request);
            return "redirect:/";
        } catch (Throwable e) {
            e.printStackTrace();
            return "redirect:/home?error=" + e.getMessage();
        }
    }
}
