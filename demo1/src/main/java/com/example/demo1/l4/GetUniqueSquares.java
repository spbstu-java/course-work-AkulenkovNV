package com.example.demo1.l4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetUniqueSquares {
    public static List<Integer> getUniqueSquares(List<Integer> numbers) {
        Map<Integer, Long> frequencyMap = numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey() * entry.getKey())
                .collect(Collectors.toList());
    }
}
