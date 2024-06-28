package com.myweb.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close(Connection conn, PreparedStatement ps) {
        try {
            conn.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
