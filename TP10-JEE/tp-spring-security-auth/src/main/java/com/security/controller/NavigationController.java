package com.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NavigationController {

    @GetMapping("/login")
    public String showLoginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null && error.equals("true")) {
            model.addAttribute("errorMessage", "Identifiants invalides. Veuillez vérifier votre nom d'utilisateur et votre mot de passe.");
        }

        if (logout != null && logout.equals("true")) {
            model.addAttribute("successMessage", "Déconnexion effectuée avec succès.");
        }

        return "auth/signin";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("currentUser", auth.getName());
        return "dashboard/overview";
    }

    @GetMapping("/admin")
    public String showAdminPanel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("currentUser", auth.getName());
        return "dashboard/admin-panel";
    }

    @GetMapping("/management")
    public String showManagementPanel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("currentUser", auth.getName());
        return "dashboard/management-panel";
    }

    @GetMapping("/user")
    public String showUserPanel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("currentUser", auth.getName());
        return "dashboard/user-panel";
    }
}