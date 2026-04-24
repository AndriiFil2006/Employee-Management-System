package dao;

import java.sql.*;
import model.User;
import util.DBConnection;

public class UserDAO {
    public User findByUsername(String username) {
        String sql = """
                    SELECT user_id, employee_id, role_id, username, password_hash, is_active, created_at, last_login
                    FROM users
                    WHERE username = ?
                """;
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new User(
                        rs.getInt("user_id"),
                        rs.getInt("employee_id"),
                        rs.getInt("role_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getBoolean("is_active"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("last_login") == null
                            ? null
                            : rs.getTimestamp("last_login").toLocalDateTime()
                    );
                }
        } catch (SQLException e) {
            System.out.println("Error finding user by username: ");
            e.printStackTrace();
        }
        return null;
    }
}