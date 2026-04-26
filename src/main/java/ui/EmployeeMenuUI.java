package ui;

import dao.EmployeeDAO;
import dao.PayrollDAO;
import model.Employee;
import model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeMenuUI {
    private final Scanner scanner = new Scanner(System.in);
    private final User loggedInUser;
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final PayrollDAO payrollDAO = new PayrollDAO();

    public EmployeeMenuUI(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1. Search employees (view only)");
            System.out.println("2. View my pay history");
            System.out.println("0. Exit\n");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> searchEmployees();
                case "2" -> payrollDAO.printEmployeePayHistory(loggedInUser.getEmployeeId());
                case "0" -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void searchEmployees() {
        System.out.println("\nSearch employees by:");
        System.out.println("1. Employee ID");
        System.out.println("2. Name");
        System.out.println("3. DOB");
        System.out.println("4. SSN");
        System.out.print("Choose option: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("Enter employee ID: ");
                int employeeId = Integer.parseInt(scanner.nextLine());

                Employee employee = employeeDAO.findByEmployeeId(employeeId);
                employeeDAO.printEmployee(employee);
            }

            case "2" -> {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                List<Employee> employees = employeeDAO.searchByName(name);

                if (employees.isEmpty()) {
                    System.out.println("No employees found.");
                    return;
                }

                for (Employee employee : employees) {
                    employeeDAO.printEmployee(employee);
                    System.out.println("------------------");
                }
            }

            case "3" -> {
                System.out.print("Enter DOB (YYYY-MM-DD): ");
                LocalDate dob = LocalDate.parse(scanner.nextLine());

                List<Employee> employees = employeeDAO.searchByDob(dob);

                if (employees.isEmpty()) {
                    System.out.println("No employees found.");
                    return;
                }

                for (Employee employee : employees) {
                    employeeDAO.printEmployee(employee);
                    System.out.println("------------------");
                }
            }

            case "4" -> {
                System.out.print("Enter SSN: ");
                String ssn = scanner.nextLine();

                Employee employee = employeeDAO.searchBySsn(ssn);
                employeeDAO.printEmployee(employee);
            }

            default -> System.out.println("Invalid option.");
        }
    }
}