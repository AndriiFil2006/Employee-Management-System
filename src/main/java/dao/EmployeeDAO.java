package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import util.DBConnection;

public class EmployeeDAO {

    private Employee mapEmployee(ResultSet rs) throws SQLException {
        Date dobDate = rs.getDate("dob");
        Date hireDate = rs.getDate("hire_date");

        LocalDate dob = dobDate == null ? null : dobDate.toLocalDate();
        LocalDate hireDateValue = hireDate == null ? null : hireDate.toLocalDate();

        return new Employee(
            rs.getInt("employee_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            dob,
            rs.getString("ssn"),
            rs.getDouble("salary"),
            hireDateValue,
            rs.getInt("division_id"),
            rs.getInt("job_title_id")
        );
    }

    public Employee findByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapEmployee(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee by ID.");
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> searchByName(String name) {
        String sql = """
            SELECT * FROM employees
            WHERE first_name LIKE ? OR last_name LIKE ?
            ORDER BY last_name, first_name
        """;

        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String searchText = "%" + name + "%";
            stmt.setString(1, searchText);
            stmt.setString(2, searchText);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                employees.add(mapEmployee(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee by name.");
            e.printStackTrace();
        }

        return employees;
    }

    public List<Employee> searchByDob(LocalDate dob) {
        String sql = "SELECT * FROM employees WHERE dob = ? ORDER BY last_name, first_name";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(dob));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                employees.add(mapEmployee(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee by DOB.");
            e.printStackTrace();
        }

        return employees;
    }

    public Employee searchBySsn(String ssn) {
        String sql = "SELECT * FROM employees WHERE ssn = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ssn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapEmployee(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee by SSN.");
            e.printStackTrace();
        }

        return null;
    }

    public int updateSalaryByPercentage(double percent, double minSalary, double maxSalary) {
        String sql = """
            UPDATE employees
            SET salary = salary * (1 + (? / 100))
            WHERE salary BETWEEN ? AND ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, percent);
            stmt.setDouble(2, minSalary);
            stmt.setDouble(3, maxSalary);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating salary by percentage.");
            e.printStackTrace();
            return 0;
        }
    }

    public void printEmployee(Employee employee) {
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("ID: " + employee.employeeId());
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("DOB: " + employee.getDob());
        System.out.println("SSN: " + employee.getSsn());
        System.out.println("Salary: $" + employee.getSalary());
        System.out.println("Hire Date: " + employee.getHireDate());
        System.out.println("Division ID: " + employee.getDivisionId());
        System.out.println("Job Title ID: " + employee.getJobTitleId());
    }

    public boolean insertEmployee(Employee employee) {
    String sql = """
        INSERT INTO employees
        (first_name, last_name, dob, ssn, salary, hire_date, division_id, job_title_id)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, employee.getFirstName());
        stmt.setString(2, employee.getLastName());
        stmt.setDate(3, Date.valueOf(employee.getDob()));
        stmt.setString(4, employee.getSsn());
        stmt.setDouble(5, employee.getSalary());
        stmt.setDate(6, Date.valueOf(employee.getHireDate()));
        stmt.setInt(7, employee.getDivisionId());
        stmt.setInt(8, employee.getJobTitleId());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error inserting employee.");
        e.printStackTrace();
        return false;
    }
}

    public boolean updateEmployee(Employee employee) {
        String sql = """
            UPDATE employees
            SET first_name = ?,
                last_name = ?,
                dob = ?,
                ssn = ?,
                salary = ?,
                hire_date = ?,
                division_id = ?,
                job_title_id = ?
            WHERE employee_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setDate(3, Date.valueOf(employee.getDob()));
            stmt.setString(4, employee.getSsn());
            stmt.setDouble(5, employee.getSalary());
            stmt.setDate(6, Date.valueOf(employee.getHireDate()));
            stmt.setInt(7, employee.getDivisionId());
            stmt.setInt(8, employee.getJobTitleId());
            stmt.setInt(9, employee.employeeId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating employee.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployee(int employeeId) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting employee.");
            e.printStackTrace();
            return false;
        }
    }
}