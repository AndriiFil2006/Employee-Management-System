import java.time.LocalDate;
import java.util.List;

import dao.EmployeeDAO;
import dao.PayrollDAO;
import dao.ReportDAO;
import model.Employee;

public class FeatureTestMain {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        PayrollDAO payrollDAO = new PayrollDAO();
        ReportDAO reportDAO = new ReportDAO();

        System.out.println("====================================");
        System.out.println("Employee Search Demo");
        System.out.println("====================================");

        Employee employee = employeeDAO.findByEmployeeId(1);
        employeeDAO.printEmployee(employee);

        System.out.println("\nSearch by name: John");
        List<Employee> nameResults = employeeDAO.searchByName("John");
        for (Employee e : nameResults) {
            employeeDAO.printEmployee(e);
            System.out.println();
        }

        System.out.println("\nSearch by DOB: 1995-06-15");
        List<Employee> dobResults = employeeDAO.searchByDob(LocalDate.of(1995, 6, 15));
        for (Employee e : dobResults) {
            employeeDAO.printEmployee(e);
            System.out.println();
        }

        System.out.println("\nSearch by SSN: 123-45-6789");
        employeeDAO.printEmployee(employeeDAO.searchBySsn("123-45-6789"));

        System.out.println("\n====================================");
        System.out.println("Salary Update Demo");
        System.out.println("====================================");

        int updated = employeeDAO.updateSalaryByPercentage(0.0, 70000, 100000);
        System.out.println(updated + " employee salaries matched the range.");
        System.out.println("Using 0.0% here so testing does not accidentally change the real database.");

        System.out.println("\n====================================");
        System.out.println("Reports Demo");
        System.out.println("====================================");

        payrollDAO.printEmployeePayHistory(1);
        reportDAO.printTotalPayByJobTitle();
        reportDAO.printTotalPayByDivision();
        reportDAO.printNewHiresByDateRange("2021-01-01", "2024-12-31");
    }
}