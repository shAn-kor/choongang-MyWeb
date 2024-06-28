package com.myweb.user.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface UserService {
    void join(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void getInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
