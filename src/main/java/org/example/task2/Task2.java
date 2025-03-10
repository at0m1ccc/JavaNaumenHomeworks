package org.example.task2;

import org.example.UserInterface;

import java.util.ArrayList;
import java.util.Random;

public class Task2 {
    private final UserInterface userInterface = new UserInterface();

    public void solveTask2() {
        userInterface.printMessage("Выполнение задания №2");
        Random random = new Random();
        int countNumber = userInterface.getUserChoice();
        ArrayList<Double> arrayList = new ArrayList<>(countNumber);
        for (int i = 0; i < countNumber; i++) {
            arrayList.add(random.nextDouble(100));
        }
        userInterface.printMessage("Неотсортированный массив: " + arrayList);
        bubbleSort(arrayList);
        userInterface.printMessage("Отсортированный массив: " + arrayList);
        userInterface.printMessage("-".repeat(50));
    }

    private void bubbleSort(ArrayList<Double> arrayList) {
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arrayList.get(j) > arrayList.get(j + 1)) {
                    Double temp = arrayList.get(j);
                    arrayList.set(j, arrayList.get(j + 1));
                    arrayList.set(j + 1, temp);
                }
            }
        }
    }
}
