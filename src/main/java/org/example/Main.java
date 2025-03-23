package org.example;

import org.example.task1.Task1;
import org.example.task2.Task2;
import org.example.task3.Task3;
import org.example.task4.Task4;
import org.example.task5.Task5;

public class Main {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        Task4 task4 = new Task4();
        Task5 task5 = new Task5();

        task1.solveTask1();
        task2.solveTask2();
        task3.solveTask3();
        task4.solveTask4();
        task5.solveTask5();
    }
}