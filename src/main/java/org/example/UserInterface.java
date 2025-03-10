package org.example;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UserInterface {
    private final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);
    private final PrintStream OUT_STREAM = System.out;

    public void printMessage(String message) {
        OUT_STREAM.println(message);
    }

    public int getUserChoice() {
        int userChoice;

        while (true) {
            OUT_STREAM.print("Введите количество элементов в массиве: ");
            if (SCANNER.hasNextInt()) {
                userChoice = SCANNER.nextInt();
                if (userChoice >= 0) return userChoice;
                OUT_STREAM.println("Количество элементов в массиве не может быть меньше 0. Повторите попытку.");
                continue;
            }
            OUT_STREAM.println("Вы ввели: " + SCANNER.nextLine() + ", а нужно было число. Повторите попытку.");
        }
    }
}
