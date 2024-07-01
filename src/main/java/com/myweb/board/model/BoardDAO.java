package com.myweb.board.model;

import com.myweb.util.JdbcUtil;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private static BoardDAO instance;

    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String uid = "HR";
    private String upw = "HR";
    private DataSource ds;
    private BoardDAO() {
        try {
            InitialContext init = new InitialContext();

            ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
        } catch (NamingException e) {
            System.out.println("커넥션 풀 에러");
        }
    }

    public static BoardDAO getInstance() {
        if(instance == null) {
            instance = new BoardDAO();
        }
        return instance;
    }

    public void regist(String writer, String title, String content) { // 글 등록
        String sql = "insert into board(bno,writer,title,content) values(board_bno.nextval,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, writer);
            ps.setString(2, title);
            ps.setString(3, content);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps);
        }
    }

    // 글 목록 조회
    public List<BoardDTO> getList() {
        String sql = "select * from board order by bno desc";
        List<BoardDTO> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int bno = rs.getInt("bno");
                String writer = rs.getString("writer");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Timestamp regdate = rs.getTimestamp("regdate");
                int hit = rs.getInt("hit");
                list.add(new BoardDTO(bno, writer, title, content, regdate, hit));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return list;
    }
}
