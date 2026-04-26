package dao;

import java.sql.*;
import util.DBConnection;

public class ReportDAO {

    public void printTotalPayByJobTitle() {
        String sql = """
            SELECT jt.job_title_name, SUM(e.salary) AS total_pay
            FROM employees e
            JOIN job_titles jt ON e.job_title_id = jt.job_title_id
            GROUP BY jt.job_title_name
            ORDER BY jt.job_title_name
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nTotal Pay by Job Title");
            System.out.println("----------------------");

            while (rs.next()) {
                System.out.printf("%s: $%.2f%n",
                    rs.getString("job_title_name"),
                    rs.getDouble("total_pay"));
            }

        } catch (SQLException e) {
            System.out.println("Error creating total pay by job title report.");
            e.printStackTrace();
        }
    }

    public void printTotalPayByDivision() {
        String sql = """
            SELECT d.division_name, SUM(e.salary) AS total_pay
            FROM employees e
            JOIN divisions d ON e.division_id = d.division_id
            GROUP BY d.division_name
            ORDER BY d.division_name
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nTotal Pay by Division");
            System.out.println("---------------------");

            while (rs.next()) {
                System.out.printf("%s: $%.2f%n",
                    rs.getString("division_name"),
                    rs.getDouble("total_pay"));
            }

        } catch (SQLException e) {
            System.out.println("Error creating total pay by division report.");
            e.printStackTrace();
        }
    }

    public void printNewHiresByDateRange(String startDate, String endDate) {
        String sql = """
            SELECT employee_id, first_name, last_name, hire_date
            FROM employees
            WHERE hire_date BETWEEN ? AND ?
            ORDER BY hire_date DESC
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nNew Hires from " + startDate + " to " + endDate);
            System.out.println("----------------------------------------");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.printf("ID %d: %s %s | Hire Date: %s%n",
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("hire_date"));
            }

            if (!found) {
                System.out.println("No new hires found in this date range.");
            }

        } catch (SQLException e) {
            System.out.println("Error creating new hires report.");
            e.printStackTrace();
        }
    }
}