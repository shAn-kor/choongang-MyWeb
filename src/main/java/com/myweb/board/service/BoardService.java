package com.myweb.board.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface BoardService {
    void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void modifyBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}