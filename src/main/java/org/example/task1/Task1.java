package org.example.task1;

import org.example.UserInterface;

import java.util.Arrays;
import java.util.Random;

public class Task1 {
    private final UserInterface userInterface = new UserInterface();

    public void solveTask1() {
        userInterface.printMessage("Выполнение задания №1");
        Random random = new Random();
        int[] array = new int[userInterface.getUserChoice()];
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
            sum += array[i];
        }
        double average = (double) sum / array.length;
        userInterface.printMessage("Массив: " + Arrays.toString(array));
        userInterface.printMessage("Среднее значение элементов в массиве: " + average);
        userInterface.printMessage("-".repeat(50));
    }
}
