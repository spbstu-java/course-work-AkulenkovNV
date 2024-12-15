package com.example.demo1.l3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class l3Controller {

    @FXML
    private TextField dictionaryFilePathField;

    @FXML
    private TextField textFilePathField;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button loadDictionaryButton;

    @FXML
    private Button chooseDictionaryButton;

    @FXML
    private Button chooseTextButton;

    @FXML
    private Button translateButton;

    private Translator translator;

    @FXML
    public void initialize() {
        chooseDictionaryButton.setOnAction(event -> chooseFile(dictionaryFilePathField));
        chooseTextButton.setOnAction(event -> chooseFile(textFilePathField));
        loadDictionaryButton.setOnAction(event -> loadDictionary());
        translateButton.setOnAction(event -> translateText());
    }

    private void chooseFile(TextField targetField) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            targetField.setText(file.getAbsolutePath());
        }
    }

    private void loadDictionary() {
        String filePath = dictionaryFilePathField.getText().trim();
        if (filePath.isEmpty()) {
            outputTextArea.appendText("Укажите путь к файлу словаря.\n");
            return;
        }

        try {
            DictionaryLoader loader = new DictionaryLoader(filePath);
            translator = new Translator(loader.getDictionary());
            outputTextArea.appendText("Словарь успешно загружен.\n");
        } catch (FileReadException | InvalidFileFormatException e) {
            outputTextArea.appendText("Ошибка: " + e.getMessage() + "\n");
        } catch (IOException e) {
            outputTextArea.appendText("Ошибка чтения файла: " + e.getMessage() + "\n");
        }
    }

    private void translateText() {
        if (translator == null) {
            outputTextArea.appendText("Словарь не загружен. Пожалуйста, загрузите словарь перед переводом.\n");
            return;
        }

        String inputText = inputTextArea.getText().trim();
        if (!textFilePathField.getText().trim().isEmpty()) {
            // Если указан файл для перевода, загружаем текст из него
            try {
                File file = new File(textFilePathField.getText().trim());
                inputText = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                inputTextArea.setText(inputText); // Заполняем поле текстом из файла
            } catch (IOException e) {
                outputTextArea.appendText("Ошибка чтения файла текста: " + e.getMessage() + "\n");
                return;
            }
        }

        if (inputText.isEmpty()) {
            outputTextArea.appendText("Введите текст для перевода.\n");
            return;
        }

        final String finalInputText = inputText;
        Platform.runLater(() -> {
            String translatedText = translator.translate(finalInputText);
            outputTextArea.appendText("Перевод:\n" + translatedText + "\n");
        });
    }
}
