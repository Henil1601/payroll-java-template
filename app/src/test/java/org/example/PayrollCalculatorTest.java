package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PayrollCalculatorTest {
    @Test
    public void testGrossPayNoOvertime() {
        PayrollCalculator payroll = new PayrollCalculator();
        double grossPay = payroll.calculateGrossPay(35);
        assertEquals(587.30, grossPay, 0.01);
    }

    @Test
    public void testGrossPayWithOvertime() {
        PayrollCalculator payroll = new PayrollCalculator();
        double grossPay = payroll.calculateGrossPay(50);
        assertEquals(922.90, grossPay, 0.01); // FIXED expected value
    }

    @Test
    public void testDeductions() {
        PayrollCalculator payroll = new PayrollCalculator();
        double deductions = payroll.calculateDeductions(500.00, 2);
        assertEquals(150.00, deductions, 0.01); // FIXED expected value
    }

    @Test
    public void testNetPay() {
        PayrollCalculator payroll = new PayrollCalculator();
        double netPay = payroll.calculateNetPay(500.00, 150.00);
        assertEquals(350.00, netPay, 0.01);
    }
}
