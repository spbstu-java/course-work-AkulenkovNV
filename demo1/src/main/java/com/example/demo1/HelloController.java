package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HelloController {

    @FXML
    private TextArea outputArea;

    @FXML
    private void handleTask1() {
        outputArea.setText("Результат выполнения задания 1...");
    }

    @FXML
    private void handleTask2() {
        outputArea.setText("Результат выполнения задания 2...");
    }

    @FXML
    private void handleTask3() {
        outputArea.setText("Результат выполнения задания 3...");
    }

    @FXML
    private void handleTask4() {
        outputArea.setText("Результат выполнения задания 4...");
    }
}
