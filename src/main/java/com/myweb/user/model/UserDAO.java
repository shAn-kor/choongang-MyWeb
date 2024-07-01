package com.myweb.user.model;

import com.myweb.util.JdbcUtil;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public int updateUser(UserDTO dto) {
        String sql = "UPDATE USERS SET pw = ?, name = ?, email = ?, gender = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getPw());
            ps.setString(2, dto.getName());
            ps.setString(3, dto.getEmail());
            ps.setString(4, dto.getGender());
            ps.setString(5, dto.getId());

            result = ps.executeUpdate(); // 반환 결과 성공 : 1, 실패 : 0

            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps);
        }
        return result;
    }

    public void deleteUser(String id) {
        String sql = "DELETE FROM USERS WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps);
        }
    }

    public UserDTO login(String id, String pw) {
        String sql = "select * from users where id=? and pw=?";
        List<String> params = new ArrayList<>();
        params.add(id);
        params.add(pw);
        return getDTOBySql(sql, params);
    }

    public UserDTO getUser(String id) {
        List<String> params = new ArrayList<>();
        params.add(id);
        String sql = "select * from users where id=?";
        return getDTOBySql(sql, params);
    }

    private UserDTO getDTOBySql (String sql, List<String> params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= params.size(); i++) {
                ps.setString(i, params.get(i - 1));
            }
            rs = ps.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id");
                String pw = rs.getString("pw");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                Timestamp regdate = rs.getTimestamp("regdate");

                return new UserDTO(id, null, name, email, gender, regdate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }

        return null;
    }
}
