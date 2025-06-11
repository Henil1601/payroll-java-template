package org.example;

public class PayrollCalculator {
    private static final double HOURLY_RATE = 16.78;
    private static final double OVERTIME_RATE = HOURLY_RATE * 1.5;
    private static final double SOCIAL_SECURITY_TAX = 0.06;
    private static final double FEDERAL_INCOME_TAX = 0.14;
    private static final double STATE_INCOME_TAX = 0.05;
    private static final double UNION_DUES = 10.00;

    public double calculateGrossPay(double hoursWorked) {
        if (hoursWorked <= 40) {
            return hoursWorked * HOURLY_RATE;
        } else {
            double regularPay = 40 * HOURLY_RATE;
            double overtimePay = (hoursWorked - 40) * OVERTIME_RATE;
            return regularPay + overtimePay;
        }
    }

    public double calculateDeductions(double grossPay, int dependents) {
        double insurance = dependents >= 3 ? 35.00 : 15.00;
        double totalDeductions = (grossPay * SOCIAL_SECURITY_TAX) +
                                 (grossPay * FEDERAL_INCOME_TAX) +
                                 (grossPay * STATE_INCOME_TAX) +
                                 UNION_DUES +
                                 insurance;
        return totalDeductions;
    }

    public double calculateNetPay(double grossPay, double deductions) {
        return grossPay - deductions;
    }
}
