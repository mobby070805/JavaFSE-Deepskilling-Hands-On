package com.forecast;

public class FinancialForecast {

    public static double calculateFutureValue(double principal, double annualRate, int years) {
        if (years == 0) {
            return principal;
        }
        return calculateFutureValue(principal * (1 + annualRate), annualRate, years - 1);
    }

    public static void main(String[] args) {
        double initialAmount = 8000.00;
        double growthRate = 0.12;
        int forecastYears = 4;

        double predictedValue = calculateFutureValue(initialAmount, growthRate, forecastYears);

        System.out.println("Initial Amount: ₹" + initialAmount);
        System.out.println("Annual Growth Rate: " + (growthRate * 100) + "%");
        System.out.println("Forecast Duration: " + forecastYears + " years");
        System.out.println("Predicted Future Value: ₹" + String.format("%.2f", predictedValue));
    }
}
