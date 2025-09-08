package com.devquiz.digitsquaresum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaliy Khomenko

The MySubmission class extends a base class DigitSquareSum and implements three main tasks:

- Calculate the sum of squares of digits for a number.
- Generate a CSV of numbers 1 to 500 and their digit square sums.
- Find which numbers between 1 and 500 eventually reach 1 (not 89) when repeatedly applying digit square sum.
- Count how many numbers from 1 to 10 million reach 89 after more than 7 iterations.
 */

public class MySubmission extends DigitSquareSum {
    private static final String MY_NAME = "Vitaliy Khomenko";

    public MySubmission() {
        super(MY_NAME);
    }

    @Override
    public int digitSquareSum(int n) {
        int sum = 0;
        while (n != 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    @Override
    public File questionOne() {
        File csvFile = new File("digitSquareSum.csv");
        try (FileWriter writer = new FileWriter(csvFile)) {
            for (int i = 1; i <= 500; i++) {
                int digitSquareSum = digitSquareSum(i);
                writer.write(i + "," + digitSquareSum + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nquestionOne ->\n" + "List of results for digitSquareSum() (numbers 1 to 500) saved to file: " + csvFile);
        return csvFile;
    }

    @Override
    public String questionTwo() {
        StringBuilder result = new StringBuilder();
        int lastNumber;

        for (int i = 1; i <= 500; i++) {
            lastNumber = i;

            while (lastNumber != 89) {
                lastNumber = digitSquareSum(lastNumber);

                if (lastNumber == 1) {
                    result
                            .append(i)
                            .append(",");
                    break;
                }
            }
        }
        System.out.println("\nquestionTwo ->\n" + "List of numbers arriving at 1 after multiple iterations of performing digitSquareSum():\n" + result.substring(0, result.length() - 1));
        return result.substring(0, result.length() - 1);
    }

    @Override
    public Integer questionThree() {
        int execResult = 0;
        int limit = 10000000;
        for (int i = 1; i <= limit; i++) {
            int n = i;
            int execCount = 0;
            while (n != 1 && n != 89) {
                n = digitSquareSum(n);
                execCount++;
            }
            if (n == 89 && execCount > 7) {
                execResult++;
            }
        }
        System.out.println("\nquestionThree ->\n" + execResult + " - TOTAL count of numbers (between 1 and 10 million inclusively) reaching 89 after 7 passes of digitSquareSum().\n");
        return execResult;
    }
}
