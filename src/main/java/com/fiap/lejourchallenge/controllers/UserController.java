package com.fiap.lejourchallenge.controllers;

import com.fiap.lejourchallenge.models.User;
import com.fiap.lejourchallenge.service.UserBuscaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private final UserBuscaService userBuscaService;

    public UserController(UserBuscaService userBuscaService) {
        this.userBuscaService = userBuscaService;
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String UserForm() {

        return "userForm";
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public String UserSearch(User user,
                             Model model) {

        model.addAttribute("userResult", userBuscaService.getUsersByParams(user));

        return "userForm";
    }
}
