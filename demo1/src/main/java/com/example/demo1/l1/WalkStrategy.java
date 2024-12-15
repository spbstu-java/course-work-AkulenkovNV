package com.example.demo1.l1;

// Реализация стратегии: Пешком
public class WalkStrategy implements MoveStrategy {
    @Override
    public void move(int x, int y, Hero hero) {
        /*System.out.println("Герой идет пешком к координатам (" + x + ", " + y + ").");*/
        hero.addCoordinates(x, y);
    }

    @Override
    public String getName() {
        return "шага";
    }
}
