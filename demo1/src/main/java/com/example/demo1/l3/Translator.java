package com.example.demo1.l3;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

public class Translator {
    private Map<String, String> dictionary;

    public Translator(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public String translate(String input) {
        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();

            // Найти все подходящие фразы из словаря
            String bestMatch = word;
            for (String dictWord : dictionary.keySet()) {
                List<String> dictWordsList = Arrays.asList(dictWord.split(" "));
                int len = dictWordsList.size();
                if (i + len <= words.length) {
                    List<String> inputWordsList = Arrays.asList(Arrays.copyOfRange(words, i, i + len));
                    if (inputWordsList.equals(dictWordsList)) {
                        if (dictWord.length() > bestMatch.length()) {
                            bestMatch = dictWord;
                        }
                    }
                }
            }

            // Выполнить перевод
            if (dictionary.containsKey(bestMatch)) {
                result.append(dictionary.get(bestMatch)).append(" ");
                i += bestMatch.split(" ").length - 1; // Пропустить слова, если фраза из нескольких слов
            } else {
                result.append(words[i]).append(" ");
            }
        }

        return result.toString().trim();
    }
}
