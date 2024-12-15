package com.example.demo1.l4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertToMap {
    public static Map<Character, String> convertToMap(List<String> strings) {
        return strings.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0), // первый символ – ключ
                        s -> s.substring(1) // остальная часть – значение
                ));
    }
}
