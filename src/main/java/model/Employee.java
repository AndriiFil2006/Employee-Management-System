package model;

import java.time.LocalDate;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;

    private LocalDate dob;
    private String ssn;
    private double salary;
    private LocalDate hireDate;

    private int divisionId;
    private int jobTitleId;

    public Employee(int employeeId, String firstName, String lastName,
                    LocalDate dob, String ssn,
                    double salary, LocalDate hireDate,
                    int divisionId, int jobTitleId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.ssn = ssn;
        this.salary = salary;
        this.hireDate = hireDate;
        this.divisionId = divisionId;
        this.jobTitleId = jobTitleId;
    }       

    public Employee(String firstName, String lastName, LocalDate dob, String ssn, double salary, LocalDate hireDate, int divisionId, int jobTitleId)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.ssn = ssn;
        this.salary = salary;
        this.hireDate = hireDate;
        this.divisionId = divisionId;
        this.jobTitleId = jobTitleId;
    }

    public int employeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getSsn() {
        return ssn;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public int getJobTitleId() {
        return jobTitleId;
    }   

    public void setSalary(double salary) { this.salary = salary; }
    public void setDivisionId(int divisionId) { this.divisionId = divisionId; }
    public void setJobTitleId(int jobTitleId) { this.jobTitleId = jobTitleId; }
}
