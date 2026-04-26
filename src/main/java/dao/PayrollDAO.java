package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Payroll;
import util.DBConnection;

public class PayrollDAO {

    private Payroll mapPayroll(ResultSet rs) throws SQLException {
        return new Payroll(
            rs.getInt("payroll_id"),
            rs.getInt("employee_id"),
            rs.getDate("pay_date").toLocalDate(),
            rs.getDouble("gross_pay"),
            rs.getDouble("net_pay"),
            rs.getDouble("taxes"),
            rs.getDouble("deductions")
        );
    }

    public List<Payroll> getPayHistoryByEmployeeId(int employeeId) {
        String sql = """
            SELECT payroll_id, employee_id, pay_date, gross_pay, net_pay, taxes, deductions
            FROM payroll
            WHERE employee_id = ?
            ORDER BY pay_date DESC
        """;

        List<Payroll> payHistory = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                payHistory.add(mapPayroll(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error getting employee pay history.");
            e.printStackTrace();
        }

        return payHistory;
    }

    public void printEmployeePayHistory(int employeeId) {
        List<Payroll> payHistory = getPayHistoryByEmployeeId(employeeId);

        System.out.println("\nPay Statement History");
        System.out.println("---------------------");

        if (payHistory.isEmpty()) {
            System.out.println("No pay history found.");
            return;
        }

        for (Payroll p : payHistory) {
            System.out.printf(
                "Pay Date: %s | Gross: $%.2f | Net: $%.2f | Taxes: $%.2f | Deductions: $%.2f%n",
                p.getPayDate(),
                p.getGrossPay(),
                p.getNetPay(),
                p.getTaxes(),
                p.getDeductions()
            );
        }
    }
}