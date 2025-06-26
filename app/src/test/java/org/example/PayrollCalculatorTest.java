package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PayrollCalculatorTest {

    PayrollCalculator calculator = new PayrollCalculator();

    @Test
    void testCalculateGrossPay_NoOvertime() {
        double result = calculator.calculateGrossPay(30, 20);
        assertEquals(600.00, result, 0.01);
    }

    @Test
    void testCalculateGrossPay_WithOvertime() {
        double result = calculator.calculateGrossPay(45, 20);
        assertEquals(950.00, result, 0.01); // 40*20 + 5*30 = 800 + 150
    }

    @Test
    void testCalculateDeductions_WithFewDependents() {
        double[] result = calculator.calculateDeductions(1000, 2);
        double[] expected = {60.00, 140.00, 50.00, 10.00, 15.00};
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    void testCalculateDeductions_WithManyDependents() {
        double[] result = calculator.calculateDeductions(1000, 4);
        double[] expected = {60.00, 140.00, 50.00, 10.00, 35.00};
        assertTrue(Arrays.equals(expected, result));
    }

    @Test
    void testCalculateLifeInsurance() {
        assertEquals(0.00, calculator.calculateLifeInsurance(1), 0.01);
        assertEquals(5.00, calculator.calculateLifeInsurance(2), 0.01);
        assertEquals(10.00, calculator.calculateLifeInsurance(3), 0.01);
        assertEquals(15.00, calculator.calculateLifeInsurance(4), 0.01);
    }

    @Test
    void testCalculateNetPay_EnoughMoney() {
        double[] deductions = {60.00, 140.00, 50.00, 10.00, 15.00};
        double life = 5.00;
        double net = calculator.calculateNetPay(1000.00, deductions, life);
        assertEquals(720.00, net, 0.01);
    }

    @Test
    void testCalculateNetPay_NotEnoughMoney() {
        double[] deductions = {6.00, 10.00, 5.00, 10.00, 15.00}; // low taxes, but large extras
        double life = 5.00;
        double net = calculator.calculateNetPay(30.00, deductions, life); // net will be too small
        assertEquals(9.00, net, 0.01);
    }
}
