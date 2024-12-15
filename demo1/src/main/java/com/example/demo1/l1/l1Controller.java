package com.example.demo1.l1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class l1Controller {

    public TextArea logArea;
    @FXML
    private TextField xCoordinateField;

    @FXML
    private TextField yCoordinateField;

    @FXML
    private Label heroCoordinatesLabel;

    @FXML
    private Button walkButton;

    @FXML
    private Button horseRideButton;

    @FXML
    private Button flyButton;

    private Hero hero;

    @FXML
    public void initialize() {
        hero = new Hero(0, 0);
        updateHeroCoordinates();

        walkButton.setOnAction(event -> hero.setMoveStrategy(new WalkStrategy()));
        horseRideButton.setOnAction(event -> hero.setMoveStrategy(new HorseRideStrategy()));
        flyButton.setOnAction(event -> hero.setMoveStrategy(new FlyStrategy()));
    }

    @FXML
    private void moveHero() {
        try {
            int newX = Integer.parseInt(xCoordinateField.getText());
            int newY = Integer.parseInt(yCoordinateField.getText());
            hero.move(newX, newY);
            heroCoordinatesLabel.setText("Текущие координаты героя: (" + hero.getX() + ", " + hero.getY() + ")");
            logArea.setText("Герой переместился на " + hero.getX() + ", " + hero.getY() + " при помощи " + hero.getMoveStrategy().getName());
        } catch (NumberFormatException e) {
            logArea.setText("Пожалуйста, введите число");
        } catch (Exception e) {
            logArea.setText(e.getMessage());
        }
    }

    private void updateHeroCoordinates() {
        heroCoordinatesLabel.setText("Текущие координаты героя: (" + hero.getX() + ", " + hero.getY() + ")");
    }

}
