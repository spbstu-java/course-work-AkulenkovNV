package com.example.demo1.l2;

public class MyClass {

    // Публичные методы
    @RunCount(3) // Аннотируем метод с параметром 3
    public void publicMethod(String message) {
        System.out.println("Публичный метод: " + message);
    }

    public void anotherPublicMethod(int number) {
        System.out.println("Другой публичный метод, число: " + number);
    }

    // Защищенные методы
    @RunCount(2) // Аннотируем метод с параметром 2
    protected void protectedMethod(int a, int b) {
        System.out.println("Защищенный метод, сумма: " + (a + b));
    }

    protected void anotherProtectedMethod() {
        System.out.println("Другой защищенный метод.");
    }

    // Приватные методы
    @RunCount(5) // Аннотируем метод с параметром 5
    private void privateMethod(String str, int n) {
        System.out.println("Приватный метод, строка: " + str + ", число: " + n);
    }

    private void anotherPrivateMethod() {
        System.out.println("Другой приватный метод.");
    }
}
