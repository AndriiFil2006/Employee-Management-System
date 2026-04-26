package ui;

import dao.EmployeeDAO;
import dao.ReportDAO;
import model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HRAdminMenuUI {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final ReportDAO reportDAO = new ReportDAO();

    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== HR Admin Menu =====");
            System.out.println("1. Search employee by ID");
            System.out.println("2. Search employee by name");
            System.out.println("3. Search employee by DOB");
            System.out.println("4. Search employee by SSN");
            System.out.println("5. Update salaries by percentage");
            System.out.println("6. Report: Total pay by job title");
            System.out.println("7. Report: Total pay by division");
            System.out.println("8. Report: New hires by date range");
            System.out.println("9. Add new employee");
            System.out.println("10. Update employee");
            System.out.println("11. Delete employee");
            System.out.println("0. Exit\n");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> searchById();
                case "2" -> searchByName();
                case "3" -> searchByDob();
                case "4" -> searchBySsn();
                case "5" -> updateSalaries();
                case "6" -> {
                    System.out.print("Enter month (YYYY-MM): ");
                    String yearMonth = scanner.nextLine();
                    reportDAO.printTotalPayByJobTitle(yearMonth);
                }

                case "7" -> {
                    System.out.print("Enter month (YYYY-MM): ");
                    String yearMonth = scanner.nextLine();
                    reportDAO.printTotalPayByDivision(yearMonth);
                }
                case "8" -> newHiresReport();
                case "9" -> addEmployee();
                case "10" -> updateEmployee();
                case "11" -> deleteEmployee();
                case "0" -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void searchById() {
        System.out.print("Enter employee ID: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        Employee employee = employeeDAO.findByEmployeeId(employeeId);
        employeeDAO.printEmployee(employee);
    }

    private void searchByName() {
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

    private void searchByDob() {
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

    private void searchBySsn() {
        System.out.print("Enter SSN: ");
        String ssn = scanner.nextLine();

        Employee employee = employeeDAO.searchBySsn(ssn);
        employeeDAO.printEmployee(employee);
    }

    private void updateSalaries() {
        System.out.print("Enter raise percentage: ");
        double percent = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter minimum salary: ");
        double minSalary = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter maximum salary: ");
        double maxSalary = Double.parseDouble(scanner.nextLine());

        int updatedRows = employeeDAO.updateSalaryByPercentage(percent, minSalary, maxSalary);

        System.out.println(updatedRows + " employee salaries updated.");
    }

    private void newHiresReport() {
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        reportDAO.printNewHiresByDateRange(startDate, endDate);
    }


    private void addEmployee() {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("DOB (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());

        System.out.print("SSN: ");
        String ssn = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        System.out.print("Hire date (YYYY-MM-DD) [leave blank for today]: ");
        String hireDateInput = scanner.nextLine();

        LocalDate hireDate;

        if (hireDateInput.isBlank()) {
            hireDate = LocalDate.now();
        } else {
            hireDate = LocalDate.parse(hireDateInput);
        }

        System.out.print("Division ID: ");
        int divisionId = Integer.parseInt(scanner.nextLine());

        System.out.print("Job Title ID: ");
        int jobTitleId = Integer.parseInt(scanner.nextLine());

        Employee employee = new Employee(
                firstName,
                lastName,
                dob,
                ssn,
                salary,
                hireDate,
                divisionId,
                jobTitleId
        );

        boolean success = employeeDAO.insertEmployee(employee);

        if (success) {
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Employee could not be added.");
        }
    }

    private void updateEmployee() {
        System.out.print("Enter employee ID to update: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        Employee existing = employeeDAO.findByEmployeeId(employeeId);

        if (existing == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Current employee data:");
        employeeDAO.printEmployee(existing);

        System.out.println("\nPress Enter to keep the current value.");

        System.out.print("First name [" + existing.getFirstName() + "]: ");
        String firstNameInput = scanner.nextLine();
        String firstName = firstNameInput.isBlank() ? existing.getFirstName() : firstNameInput;

        System.out.print("Last name [" + existing.getLastName() + "]: ");
        String lastNameInput = scanner.nextLine();
        String lastName = lastNameInput.isBlank() ? existing.getLastName() : lastNameInput;

        System.out.print("DOB [" + existing.getDob() + "]: ");
        String dobInput = scanner.nextLine();
        LocalDate dob = dobInput.isBlank() ? existing.getDob() : LocalDate.parse(dobInput);

        System.out.print("SSN [" + existing.getSsn() + "]: ");
        String ssnInput = scanner.nextLine();
        String ssn = ssnInput.isBlank() ? existing.getSsn() : ssnInput;

        System.out.print("Salary [" + existing.getSalary() + "]: ");
        String salaryInput = scanner.nextLine();
        double salary = salaryInput.isBlank() ? existing.getSalary() : Double.parseDouble(salaryInput);

        System.out.print("Hire date [" + existing.getHireDate() + "]: ");
        String hireDateInput = scanner.nextLine();
        LocalDate hireDate = hireDateInput.isBlank() ? existing.getHireDate() : LocalDate.parse(hireDateInput);

        System.out.print("Division ID [" + existing.getDivisionId() + "]: ");
        String divisionInput = scanner.nextLine();
        int divisionId = divisionInput.isBlank() ? existing.getDivisionId() : Integer.parseInt(divisionInput);

        System.out.print("Job Title ID [" + existing.getJobTitleId() + "]: ");
        String jobTitleInput = scanner.nextLine();
        int jobTitleId = jobTitleInput.isBlank() ? existing.getJobTitleId() : Integer.parseInt(jobTitleInput);

        Employee updatedEmployee = new Employee(
                employeeId,
                firstName,
                lastName,
                dob,
                ssn,
                salary,
                hireDate,
                divisionId,
                jobTitleId
        );

        boolean success = employeeDAO.updateEmployee(updatedEmployee);

        if (success) {
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee could not be updated.");
        }
    }

    private void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        Employee employee = employeeDAO.findByEmployeeId(employeeId);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        employeeDAO.printEmployee(employee);

        System.out.print("Are you sure you want to delete this employee? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            boolean success = employeeDAO.deleteEmployee(employeeId);

            if (success) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee could not be deleted.");
            }
        } else {
            System.out.println("Delete canceled.");
        }
    }
}