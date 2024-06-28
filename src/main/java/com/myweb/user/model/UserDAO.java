package com.myweb.user.model;

import com.myweb.util.JdbcUtil;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static UserDAO instance;

    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String uid = "HR";
    private String upw = "HR";
    private DataSource ds;

    private UserDAO() {
        // 커넥션 풀에 사용할 객체 얻어옴
        try {
            InitialContext init = new InitialContext();

            ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
        } catch (NamingException e) {
            System.out.println("커넥션 풀 에러");
        }
    }
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

//    public int findUser(String id) {
//        int result = 0;
//
//        String sql = "select * from users where id=?";
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = ds.getConnection();
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                result = 1;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            JdbcUtil.close(conn, ps, rs);
//        }
//
//        return result;
//    }

    // 회원가입
    public void insertUser(UserDTO dto) {
        String sql = "INSERT INTO users (id, pw, name, email, gender) VALUES (?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getId());
            ps.setString(2, dto.getPw());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getEmail());
            ps.setString(5, dto.getGender());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps);
        }
    }

    public UserDTO login(String id, String pw) {
        UserDTO dto = null;
        String sql = "select * from users where id=? and pw=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pw);
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                dto = new UserDTO(id, null, name, email, gender, null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return dto;
    }

    public UserDTO getUser(String id) {
        UserDTO dto = null;

        String sql = "select * from users where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                dto = new UserDTO(id, null, name, email, gender, null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }

        return dto;
    }
}
