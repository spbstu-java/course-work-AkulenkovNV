package com.example.demo1.l1;

public class Hero {
    private int x; // Координата x
    private int y; // Координата y
    private MoveStrategy moveStrategy; // Стратегия перемещения

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Конструктор, который инициализирует начальные координаты
    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Устанавливаем стратегию перемещения
    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    // Метод перемещения героя к новым координатам
    public void move(int newX, int newY) {
        if (moveStrategy != null) {
            moveStrategy.move(newX, newY, this);
            //System.out.println("Герой переместился в новые координаты (" + x + ", " + y + ").");
        } else {
            throw new RuntimeException("Не выбрана стратегия движения");
            //System.out.println("Способ перемещения не выбран.");
        }
    }

    public void addCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    // Получение текущих координат
    public void getCoordinates() {
        System.out.println("Текущие координаты героя: (" + x + ", " + y + ").");
    }
}
