package org.example;

public class PayrollCalculator {
    private static final double DEFAULT_PAY_RATE = 16.78;
    private static final double OVERTIME_RATE_MULTIPLIER = 1.5;

    public double calculateGrossPay(double hours, double rate) {
        if (hours <= 40) {
            return hours * rate;
        } else {
            double overtimeHours = hours - 40;
            return (40 * rate) + (overtimeHours * rate * OVERTIME_RATE_MULTIPLIER);
        }
    }

    public double[] calculateDeductions(double gross, int dependents) {
        double socSec = gross * 0.06;
        double fedTax = gross * 0.14;
        double stateTax = gross * 0.05;
        double union = 10.00;
        double insurance = dependents >= 3 ? 35.00 : 15.00;
        return new double[]{socSec, fedTax, stateTax, union, insurance};
    }

    public double calculateLifeInsurance(int choice) {
        switch (choice) {
            case 2: return 5.00;
            case 3: return 10.00;
            case 4: return 15.00;
            default: return 0.00;
        }
    }

    public double calculateNetPay(double gross, double[] deductions, double lifeInsurance) {
        double totalDeductions = deductions[0] + deductions[1] + deductions[2];
        double tempNet = gross - totalDeductions;
        double totalExtras = deductions[3] + deductions[4] + lifeInsurance;

        if (tempNet >= totalExtras) {
            return tempNet - totalExtras;
        } else {
            return tempNet; // Still print, and list "owed" values separately
        }
    }

    public void printDeductionsBreakdown(double[] deductions, double lifeInsurance) {
        System.out.printf("  SocSec:   $ %.2f\n", deductions[0]);
        System.out.printf("  FedTax:   $ %.2f\n", deductions[1]);
        System.out.printf("   StTax:   $ %.2f\n", deductions[2]);
        System.out.printf("   Union:   $ %.2f\n", deductions[3]);
        System.out.printf("     Ins:   $ %.2f\n", deductions[4]);
        if (lifeInsurance > 0) {
            System.out.printf(" LifeIns:   $ %.2f\n", lifeInsurance);
        }
    }

    public void printOutstandingDues(double net, double[] deductions, double lifeInsurance) {
        double totalDeductions = deductions[0] + deductions[1] + deductions[2];
        double tempNet = net;
        double totalExtras = deductions[3] + deductions[4] + lifeInsurance;

        if (tempNet < totalExtras) {
            System.out.println("\nThe employee still owes:");
            if (deductions[3] > 0) System.out.printf("   Union:   $ %.2f\n", deductions[3]);
            if (deductions[4] > 0) System.out.printf("     Ins:   $ %.2f\n", deductions[4]);
            if (lifeInsurance > 0) System.out.printf(" LifeIns:   $ %.2f\n", lifeInsurance);
        }
    }
}
