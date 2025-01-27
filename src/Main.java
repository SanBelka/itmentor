import java.util.Scanner;

import static java.lang.System.*;

class Main {
    public static void main(String[] args) { // Это основная функция приложения как инт мейн
        Scanner scanner = new Scanner(in); // Объект созданный для сканирования введенных значений в консоли(System.in) - обозначение ввода консоли
        out.println("Введите арифметическое выражение (например, 2 + 2):");

        while (true) {
            String input = scanner.nextLine(); // Построчно читаем ввод
            if (input.equalsIgnoreCase("exit")) { // Выход из программы
                out.println("Программа завершена.");
                break;
            }

            try {
                String result = calc(input); // Вычисляем результат
                out.println("Результат: " + result);
            } catch (Exception e) {
                out.println("Ошибка: " + e.getMessage());
                // System.exit(1);
            }
        }

        scanner.close(); // Удаляем сканер
    }

    public static String calc(String input) {
        // Удаляем лишние пробелы и разбиваем строку на части
        String[] parts = input.trim().split("\\s+");

        // Проверяем, что введено ровно три части: число, оператор, число
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения. Ожидается: число оператор число");
        }

        // Парсим числа
        int num1;
        int num2;
        try {
            num1 = Integer.parseInt(parts[0]);
            num2 = Integer.parseInt(parts[2]);
            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно.");

            }
        } catch (NumberFormatException e) {

            throw new IllegalArgumentException("Неверный формат числа.");

        }

        // Определяем оператор
        String operator = parts[1];
        int result;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Деление на ноль невозможно.");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор. Допустимые операторы: +, -, *, /");
        }

        // Возвращаем результат в виде строки
        return String.valueOf(result);
    }
}