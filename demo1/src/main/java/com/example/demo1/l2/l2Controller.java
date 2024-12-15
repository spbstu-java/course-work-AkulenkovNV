package com.example.demo1.l2;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.lang.reflect.*;

public class l2Controller {

    @FXML
    private TextArea logArea;

    private PrintStream originalOut;
    private PrintStream originalErr;

    @FXML
    public void initialize() {
        // Сохраняем оригинальные потоки
        originalOut = System.out;
        originalErr = System.err;

        // Перенаправляем потоки вывода
        redirectSystemStreams();
    }

    private void restoreSystemStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                String text = new String(b, off, len);
                Platform.runLater(() -> logArea.appendText(text));
            }

            @Override
            public void write(int b) throws IOException {
                // Переводим байт в символ и добавляем в лог
                Platform.runLater(() -> logArea.appendText(String.valueOf((char) b)));
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    @FXML
    public void invokeAnnotatedMethods() {
        redirectSystemStreams();
        try {
            MyClass myClassInstance = new MyClass();

            // Получаем все методы класса MyClass
            Method[] methods = MyClass.class.getDeclaredMethods();

            // Проходим по каждому методу
            for (Method method : methods) {
                // Проверяем, аннотирован ли метод аннотацией @RunCount
                if (method.isAnnotationPresent(RunCount.class)) {
                    // Проверяем, является ли метод защищённым или приватным
                    if (Modifier.isProtected(method.getModifiers()) || Modifier.isPrivate(method.getModifiers())) {
                        // Получаем аннотацию
                        RunCount runCount = method.getAnnotation(RunCount.class);
                        int times = runCount.value(); // Сколько раз нужно вызвать метод

                        // Делаем метод доступным для вызова, даже если он приватный
                        method.setAccessible(true);

                        // Вызов метода столько раз, сколько указано в аннотации
                        for (int i = 0; i < times; i++) {
                            // Получаем параметры метода
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            Object[] params = getParamsForMethod(parameterTypes); // Используем отдельный метод для создания параметров

                            try {
                                // Вызов метода с параметрами
                                Object result = method.invoke(myClassInstance, params);

                                // Если метод возвращает результат, выводим его
                                if (result != null) {
                                    System.out.println("Результат: " + result.toString());
                                }
                            } catch (Exception e) {
                                System.err.println("Ошибка при вызове метода: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка выполнения: " + e.getMessage());
        } finally {
            restoreSystemStreams();
        }
    }

    // Метод для определения параметров для метода
    public static Object[] getParamsForMethod(Class<?>[] parameterTypes) {
        Object[] params = new Object[parameterTypes.length];
        for (int j = 0; j < parameterTypes.length; j++) {
            if (parameterTypes[j] == String.class) {
                params[j] = "Test String"; // Если параметр строка
            } else if (parameterTypes[j] == int.class) {
                params[j] = 42; // Если параметр целое число
            } else if (parameterTypes[j] == boolean.class) {
                params[j] = true; // Если параметр логический (boolean)
            } else if (parameterTypes[j] == double.class) {
                params[j] = 3.14; // Если параметр типа double
            } else if (parameterTypes[j] == float.class) {
                params[j] = 2.5f; // Если параметр типа float
            } else if (parameterTypes[j] == char.class) {
                params[j] = 'A'; // Если параметр типа char
            } else if (parameterTypes[j] == int[].class) {
                params[j] = new int[]{1, 2, 3}; // Если параметр массив int
            } else if (parameterTypes[j] == String[].class) {
                params[j] = new String[]{"One", "Two", "Three"}; // Если параметр массив строк
            } else {
                params[j] = null; // Если параметр неизвестен, оставляем null
            }
        }
        return params;
    }
}
