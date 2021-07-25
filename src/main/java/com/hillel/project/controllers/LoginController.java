package com.hillel.project.controllers;

import com.hillel.project.entities.User;

public interface LoginController {
    User login(String login, String pass);
    User register();
}
