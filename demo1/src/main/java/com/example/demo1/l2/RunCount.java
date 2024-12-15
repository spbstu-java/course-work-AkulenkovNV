package com.example.demo1.l2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Аннотация с целочисленным параметром
@Retention(RetentionPolicy.RUNTIME) // Аннотация будет доступна во время выполнения программы
@interface RunCount {
    int value(); // Параметр аннотации
}