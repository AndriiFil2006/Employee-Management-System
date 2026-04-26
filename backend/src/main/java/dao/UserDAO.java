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

    public boolean insertUser(User user)
    {
        String sql = """
                    INSERT INTO users (employee_id, role_id, username, password_hash, is_active)
                    VALUES (?, ?, ?, ?, ?)
                """;
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
                stmt.setInt(1, user.getEmployeeId());
                stmt.setInt(2, user.getRoleId());
                stmt.setString(3, user.getUsername());
                stmt.setString(4, user.getPasswordHash());
                stmt.setBoolean(5, user.getIsActive());

                return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting user.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLastLogin(int userId)
    {
        String sql = """
                    UPDATE users
                    SET last_login = CURRENT_TIMESTAMP
                    WHERE user_id = ?
                """;
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setInt(1, userId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating last login.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deactivateUser(int userId)
    {
        String sql = """
                    UPDATE users
                    SET is_active = FALSE
                    WHERE user_id = ?
                """;
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setInt(1, userId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deactivating a user.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean usernameExists(String username) {
        String sql = """
                    SELECT user_id
                    FROM users
                    WHERE username = ?
                """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e)
        {
            System.out.println("Error checking if username exists.");
            e.printStackTrace();
            return false;
        }
    }
}