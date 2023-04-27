package com.project.schoolmanagement.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Test Page");
        model.addAttribute("pageContent", "Welcome to our website!");
        return "test";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "About Us");
        model.addAttribute("pageContent", "Learn more about our company.");
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("pageTitle", "Contact Us");
        model.addAttribute("pageContent", "Get in touch with us!");
        return "contact";
    }
}
