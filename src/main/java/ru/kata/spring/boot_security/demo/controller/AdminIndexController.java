package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminIndexController {

    private final UserService userService;

    public AdminIndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/admin"})
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.findAll());
        return "AdminIndex";
    }
}
