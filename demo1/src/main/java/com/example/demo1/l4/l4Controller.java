package com.example.demo1.l4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.stream.Collectors;

public class l4Controller {

    @FXML
    private ComboBox<String> methodComboBox;

    @FXML
    private VBox inputFieldsBox;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button executeButton;

    @FXML
    public void initialize() {
        methodComboBox.getItems().addAll(
                "GetAverage",
                "TransformStrings",
                "GetUniqueSquares",
                "GetLastElement",
                "SumOfEvenNumbers",
                "ConvertToMap"
        );

        methodComboBox.setOnAction(event -> updateInputFields());
        executeButton.setOnAction(event -> executeMethod());
    }

    private void updateInputFields() {
        inputFieldsBox.getChildren().clear();
        String selectedMethod = methodComboBox.getValue();

        if (selectedMethod == null) return;

        switch (selectedMethod) {
            case "GetAverage":
                inputFieldsBox.getChildren().add(new Label("Введите числа через запятую:"));
                inputFieldsBox.getChildren().add(new TextField());
                break;

            case "TransformStrings":
            case "ConvertToMap":
                inputFieldsBox.getChildren().add(new Label("Введите строки через запятую:"));
                inputFieldsBox.getChildren().add(new TextField());
                break;

            case "GetUniqueSquares":
                inputFieldsBox.getChildren().add(new Label("Введите числа через запятую (возможно повторение):"));
                inputFieldsBox.getChildren().add(new TextField());
                break;

            case "GetLastElement":
                inputFieldsBox.getChildren().add(new Label("Введите элементы коллекции через запятую:"));
                inputFieldsBox.getChildren().add(new TextField());
                break;

            case "SumOfEvenNumbers":
                inputFieldsBox.getChildren().add(new Label("Введите массив чисел через запятую:"));
                inputFieldsBox.getChildren().add(new TextField());
                break;

            default:
                break;
        }
    }

    private void executeMethod() {
        String selectedMethod = methodComboBox.getValue();
        if (selectedMethod == null) {
            outputTextArea.appendText("Выберите метод.\n");
            return;
        }

        List<String> inputFields = inputFieldsBox.getChildren().stream()
                .filter(node -> node instanceof TextField)
                .map(node -> ((TextField) node).getText())
                .collect(Collectors.toList());

        if (inputFields.isEmpty() || inputFields.get(0).isEmpty()) {
            outputTextArea.appendText("Введите данные для метода.\n");
            return;
        }

        String result = "";

        try {
            switch (selectedMethod) {
                case "GetAverage":
                    List<Integer> numbers = Arrays.stream(inputFields.get(0).split(","))
                            .map(String::trim)  // Убираем пробелы
                            .map(Integer::parseInt)  // Преобразуем в целые числа
                            .collect(Collectors.toList());
                    result = "Среднее значение: " + GetAverage.getAverage(numbers);
                    break;

                case "GetUniqueSquares":
                    List<Integer> uniqueSquaresNumbers = Arrays.stream(inputFields.get(0).split(","))
                            .map(String::trim)  // Убираем пробелы
                            .map(Integer::parseInt)  // Преобразуем в целые числа
                            .collect(Collectors.toList());
                    result = "Уникальные квадраты: " + GetUniqueSquares.getUniqueSquares(uniqueSquaresNumbers);
                    break;

                case "SumOfEvenNumbers":
                    int[] array = Arrays.stream(inputFields.get(0).split(","))
                            .map(String::trim)  // Убираем пробелы
                            .mapToInt(Integer::parseInt)  // Преобразуем в целые числа
                            .toArray();
                    result = "Сумма четных чисел: " + SumOfEvenNumbers.sumOfEvenNumbers(array);
                    break;

                case "TransformStrings":
                    List<String> strings = Arrays.stream(inputFields.get(0).split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    result = "Преобразованные строки: " + TransformStrings.transformStrings(strings);
                    break;

                case "GetLastElement":
                    List<String> elements = Arrays.stream(inputFields.get(0).split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    result = "Последний элемент: " + GetLastElement.getLastElement(elements);
                    break;

                case "ConvertToMap":
                    List<String> mapStrings = Arrays.stream(inputFields.get(0).split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    Map<Character, String> map = ConvertToMap.convertToMap(mapStrings);
                    result = "Преобразование в Map: " + map;
                    break;

                default:
                    result = "Метод не поддерживается.";
            }
        } catch (NumberFormatException e) {
            outputTextArea.appendText("Введите числа через запятую.\n");
            return;
        }

        outputTextArea.appendText(result + "\n");
    }

}
