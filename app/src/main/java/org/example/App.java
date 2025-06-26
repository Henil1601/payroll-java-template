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

        double rate;
        do {
            System.out.print("Enter your hourly pay rate: ");
            rate = scanner.nextDouble();
            if (rate < 0) System.out.println("Pay rate can't be negative. Try again.");
        } while (rate < 0);

        int plan = 0;
        while (true) {
            System.out.println("\nWhich life insurance plan do you want to select?");
            System.out.println("  (1) no plan");
            System.out.println("  (2) single plan");
            System.out.println("  (3) married plan");
            System.out.println("  (4) married with children plan");
            plan = scanner.nextInt();

            if (plan == 4 && dependents == 0) {
                System.out.println("Sorry! You need at least one child to select that plan.");
            } else if (plan >= 1 && plan <= 4) {
                break;
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }

        double gross = calculator.calculateGrossPay(hours, rate);
        double[] deductions = calculator.calculateDeductions(gross, dependents);
        double lifeIns = calculator.calculateLifeInsurance(plan);
        double net = calculator.calculateNetPay(gross, deductions, lifeIns);

        System.out.println("\nPayroll Stub:\n");
        System.out.printf("   Hours:   %.1f\n", hours);
        System.out.printf("    Rate:   %.2f $/hr\n", rate);
        System.out.printf("   Gross:   $ %.2f\n\n", gross);

        calculator.printDeductionsBreakdown(deductions, lifeIns);
        System.out.printf("\n     Net:   $ %.2f\n", net);
        calculator.printOutstandingDues(gross - (deductions[0] + deductions[1] + deductions[2]), deductions, lifeIns);

        System.out.println("\nThank you for using the Payroll Program!");
        scanner.close();
    }
}
