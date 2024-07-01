package com.myweb.controller;


import com.myweb.user.service.UserService;
import com.myweb.user.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("*.user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI(); // ip, port 번호 제외된 주소
        String path = req.getContextPath(); // 프로젝트 식별 이름
        String command = uri.substring(path.length());

        System.out.println(command);

        UserService service; // 공통으로 사용할 유저 서비스 객체

        // 기본 이동방식 : forward
        // mvc2 에서 리다이렉트는 다시 컨트롤러를 태울때 사용한다.

        // 회원가입 화면
        if (command.equals("/user/join.user")) {
            req.getRequestDispatcher("join.jsp").forward(req, resp);
        }
        if (command.equals("/user/joinForm.user")) { // 회원가입 기능
            service = new UserServiceImpl();
            service.join(req, resp);
        }
        if (command.equals("/user/login.user")) { // 로그인 페이지
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
        if (command.equals("/user/loginForm.user")) { // 로그인 요청
            service = new UserServiceImpl();
            service.login(req, resp);
        }
        if (command.equals("/user/myPage.user")) { // 회원페이지
            req.getRequestDispatcher("myPage.jsp").forward(req, resp);
        }
        if (command.equals("/user/modify.user")) { // 회원 정보 수정 페이지
            service = new UserServiceImpl();
            service.getInfo(req, resp);
        }
        if (command.equals("/user/logout.user")) { // 로그아웃
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/index.jsp"); // 메인 화면으로
        }
        if (command.equals("/user/update.user")) { // 회원정보 수정
            new UserServiceImpl().update(req, resp);
        }
        if (command.equals("/user/delete.user")) { // 회원 탈퇴
            // mvc2는 기본 이동이 forward
            req.setAttribute("msg", "비밀번호를 입력하세요");
            req.getRequestDispatcher("delete.jsp").forward(req, resp);
        }
        if (command.equals("/user/deleteForm.user")) {
            new UserServiceImpl().delete(req, resp);
        }
    }
}
