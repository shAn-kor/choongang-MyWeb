package com.myweb.board.service;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardDTO;
import com.myweb.user.model.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class BoardServiceImpl implements BoardService {
    BoardDAO dao = BoardDAO.getInstance();

    @Override
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        dao.regist(writer, title, content);

        // mvc2 의 기본 이동은 forward이지만
        // service 에서 목록을 redirect 로 태워서 나간다
        response.sendRedirect("list.board");
    }

    @Override
    public void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BoardDTO> list = dao.getList();

        request.setAttribute("list", list);
        request.getRequestDispatcher("board_list.jsp").forward(request, response);
    }

    @Override
    public void modifyBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
