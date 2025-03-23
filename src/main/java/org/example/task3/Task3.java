package org.example.task3;

import org.example.UserInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task3 {
    private final UserInterface userInterface = new UserInterface();
    private final List<Employee> employees = new ArrayList<>();

    public void solveTask3() {
        userInterface.printMessage("Выполнение задания №3");
        createEmployees();
        List<Employee> sortedEmployees = employees
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .toList();
        userInterface.printMessage("Отсортированный список: " + sortedEmployees);
        userInterface.printMessage("-".repeat(50));
    }

    private void createEmployees() {
        employees.add(new Employee("Иванов Иван Иванович", 21, "Отдел разработки", 100000D));
        employees.add(new Employee("Смирнов Олег Анатольевич", 30, "Отдел продаж", 50000D));
        employees.add(new Employee("Попов Алексей Дмитриевич", 25, "Отдел разработки", 200000D));
        employees.add(new Employee("Кузнецов Андрей Игоревич", 22, "Отдел разработки", 120000D));
        employees.add(new Employee("Соколов Дмитрий Олегович", 35, "Отдел разработки", 350000D));
    }
}
