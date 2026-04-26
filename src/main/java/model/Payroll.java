package model;

import java.time.LocalDate;

public class Payroll {
    private int payrollId;
    private int employeeId;
    private LocalDate payDate;
    private double grossPay;
    private double netPay;
    private double taxes;
    private double deductions;

    public Payroll(int payrollId, int employeeId, LocalDate payDate,
                   double grossPay, double netPay, double taxes, double deductions) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.payDate = payDate;
        this.grossPay = grossPay;
        this.netPay = netPay;
        this.taxes = taxes;
        this.deductions = deductions;
    }

    public int getPayrollId() { return payrollId; }
    public int getEmployeeId() { return employeeId; }
    public LocalDate getPayDate() { return payDate; }
    public double getGrossPay() { return grossPay; }
    public double getNetPay() { return netPay; }
    public double getTaxes() { return taxes; }
    public double getDeductions() { return deductions; }
}