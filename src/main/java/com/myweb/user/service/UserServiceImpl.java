package com.myweb.user.service;

import com.myweb.user.model.UserDAO;
import com.myweb.user.model.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class UserServiceImpl implements UserService{
    UserDAO dao = UserDAO.getInstance();

    @Override
    public void join(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 값을 컨트롤러 대신 받을 수 있다.
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");

        // 중복회원 있는지 확인
        if (dao.getUser(id) != null) {
            req.setAttribute("msg", "이미 존재하는 회원입니다.");
            req.getRequestDispatcher("join.jsp").forward(req, resp);
            return;
        }

        // 중복이 없는 경우 회원가입 처리
        dao.insertUser(new UserDTO(id,pw,name,email,gender, new Timestamp(System.currentTimeMillis())));
        resp.sendRedirect("login.user");
    }

    @Override
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        UserDTO dto = dao.login(id, pw);

        if (dto == null) {
            req.setAttribute("msg", "아이디 또는 비밀번호를 확인하세요");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        // 세션에 로그인 성공에 대한 내용 저장
        HttpSession session = req.getSession(); // 리퀘스트에서 현재 세션 얻는다.
        session.setAttribute("user_id", dto.getId());
        session.setAttribute("user_name", dto.getName());

        resp.sendRedirect("myPage.user");
    }

    @Override
    public void getInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 아이디 기반으로 회원정보를 조회해서 데이터를 가지고, 수정페이지로 이동
        // 1. 아이디는 세션에 있다.
        HttpSession session = req.getSession();
        String id = (String)session.getAttribute("user_id");

        // 2. 아이디 기반으로 회원정보를 조회하는 getInfo() DAO에 생성

        // 3. 서비스 영역에서는 getInfo() 메서드 호출 후, 조회한 데이터를 request에 담는다.
        UserDTO dto = dao.getUser(id);
        req.setAttribute("user_id", dto.getId());
        req.setAttribute("user_name", dto.getName());
        req.setAttribute("user_email", dto.getEmail());
        req.setAttribute("user_gender", dto.getGender());

        // 4. forward를 이용해 modify.jsp로 이동한다.
        req.getRequestDispatcher("modify.jsp").forward(req, resp);
        // 5. 회원정보를 input 태그에 미리 출력해주면 된다.
    }
}
