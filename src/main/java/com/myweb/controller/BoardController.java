package com.myweb.controller;

import com.myweb.board.service.BoardService;
import com.myweb.board.service.BoardServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI(); // ip, port 번호 제외된 주소
        String path = request.getContextPath(); // 프로젝트 식별 이름
        String command = uri.substring(path.length());

        BoardService service;
        if (command.equals("/board/list.board")) { // 목록화면
            // mvc2 기본 이동은 forward
            service = new BoardServiceImpl();
            service.getList(request, response);
            request.getRequestDispatcher("board_list.jsp").forward(request, response);
        }
        if (command.equals("/board/write.board")) {
            request.getRequestDispatcher("board_write.jsp").forward(request, response);
        }
        if (command.equals("/board/registerForm.board")) { // 글 등록
            service = new BoardServiceImpl();
            service.regist(request, response);
        }
        if (command.equals("/board/list.board")) {}
    }
}
