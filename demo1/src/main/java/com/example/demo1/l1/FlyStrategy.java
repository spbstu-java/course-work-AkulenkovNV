package com.example.demo1.l1;

// Реализация стратегии: Лететь
public class FlyStrategy implements MoveStrategy {
    @Override
    public void move(int x, int y, Hero hero) {
        /*System.out.println("Герой летит к координатам (" + x + ", " + y + ").");*/
        hero.addCoordinates(x, y);
    }

    @Override
    public String getName() {
        return "полета";
    }
}
