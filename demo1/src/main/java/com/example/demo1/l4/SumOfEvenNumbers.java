package com.example.demo1.l4;

import java.util.Arrays;

public class SumOfEvenNumbers {
    public static int sumOfEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }
}
