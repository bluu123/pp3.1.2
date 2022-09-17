package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class UserManageControllerImpl {

    private final UserService userService;
    private final RoleService roleService;

    public UserManageControllerImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // переход
    @GetMapping("/create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("listRoles", roleService.getAllRoles());
        return "user_manage";
    }

    // переход
    @GetMapping("/update/{id}")
    public String preUpdate(ModelMap modelMap, @PathVariable("id") Long id) {
        modelMap.addAttribute("user", userService.findOne(id));
        modelMap.addAttribute("listRoles", roleService.getAllRoles());
        return "user_manage";
    }

    // сохранение
    @PostMapping("/create")
    public String create(User user,  @RequestParam("listRoles") ArrayList<Long> roles) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    // обновление
    @PostMapping("/update")
    public String update(User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    // удаление
    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        userService.remove(id);
        return "redirect:/admin";
    }
}