package com.example.demo1.l1;

// Реализация стратегии: На лошади
public class HorseRideStrategy implements MoveStrategy {
    @Override
    public void move(int x, int y, Hero hero) {
        /*System.out.println("Герой едет на лошади к координатам (" + x + ", " + y + ").");*/
        hero.addCoordinates(x, y);
    }

    @Override
    public String getName() {
        return "поездки на лошади";
    }
}
