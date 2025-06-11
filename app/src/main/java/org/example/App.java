package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollCalculator calculator = new PayrollCalculator();

        System.out.println("Welcome to the Payroll Program!");

        System.out.print("How many hours did you work this week? ");
        double hours = scanner.nextDouble();

        System.out.print("How many children do you have? ");
        int dependents = scanner.nextInt();

        double grossPay = calculator.calculateGrossPay(hours);
        double deductions = calculator.calculateDeductions(grossPay, dependents);
        double netPay = calculator.calculateNetPay(grossPay, deductions);

        System.out.println("\nPayroll Stub:\n");
        System.out.printf("   Hours:   %.1f\n", hours);
        System.out.printf("    Rate:   $%.2f/hr\n", 16.78);
        System.out.printf("   Gross:   $%.2f\n\n", grossPay);
        System.out.printf("  SocSec:   $%.2f\n", grossPay * 0.06);
        System.out.printf("  FedTax:   $%.2f\n", grossPay * 0.14);
        System.out.printf("   StTax:   $%.2f\n", grossPay * 0.05);
        System.out.printf("   Union:   $%.2f\n", 10.00);
        System.out.printf("     Ins:   $%.2f\n", dependents >= 3 ? 35.00 : 15.00);
        System.out.printf("\n     Net:   $%.2f\n", netPay);

        System.out.println("\nThank you for using the Payroll Program!");
    }
}
