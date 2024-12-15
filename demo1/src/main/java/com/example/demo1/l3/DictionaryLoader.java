package com.example.demo1.l3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DictionaryLoader {
    private Map<String, String> dictionary = new HashMap<>();

    public DictionaryLoader(String filePath) throws IOException, FileReadException, InvalidFileFormatException {
        loadDictionary(filePath);
    }

    private void loadDictionary(String filePath) throws IOException, FileReadException, InvalidFileFormatException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new InvalidFileFormatException("Неверный формат строки: " + line);
                }
                String word = parts[0].trim().toLowerCase();
                String translation = parts[1].trim();
                dictionary.put(word, translation);
            }
        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла: " + filePath, e);
        }
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }
}
