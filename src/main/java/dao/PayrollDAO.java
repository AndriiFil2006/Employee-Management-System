package dao;

import java.sql.*;
import util.DBConnection;

public class PayrollDAO {

    public void printEmployeePayHistory(int employeeId) {
        String sql = """
            SELECT employee_id, first_name, last_name, salary, hire_date
            FROM employees
            WHERE employee_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nEmployee Pay Information");
            System.out.println("------------------------");

            if (rs.next()) {
                System.out.println("Employee ID: " + rs.getInt("employee_id"));
                System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                System.out.printf("Current Salary: $%.2f%n", rs.getDouble("salary"));
                System.out.println("Hire Date: " + rs.getDate("hire_date"));
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            System.out.println("Error getting employee pay information.");
            e.printStackTrace();
        }
    }
}