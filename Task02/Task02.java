package com.epam.kusanov.Task02;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task02 {
    public void calculateExpression() {
        boolean check = true;
        // стек чисел
        Stack<Double> values = new Stack<>();
        // стек операторов
        Stack<Character> ops = new Stack<>();
        while (check) {
            System.out.println("Введите выражение: ");
            Scanner scannerInt = new Scanner(System.in);
            String str = scannerInt.nextLine();
            //проверка скобок
            int hook1 = 0;
            int lasthook1 = 0;
            int hook2 = 0;
            int lasthook2 = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    hook1++;
                    lasthook1 = i;
                }
                if (str.charAt(i) == ')') {
                    hook2++;
                    lasthook2 = i;
                }
            }
            if (hook1 != hook2 || lasthook1 > lasthook2) {
                System.out.println("Проверьте скобки");
            } else {
                //проверка повторений знаков арифметических действий
                Pattern p1 = Pattern.compile("[\\+\\-\\*\\/][\\+\\-\\*\\/]");
                Matcher m1 = p1.matcher(str);
                boolean b1 = m1.find();
                if (b1) {
                    System.out.println("Проверьте повторения знаков арифметических действий");
                } else {

                    //проверка наличия неразрешенных символов
                    Pattern p2 = Pattern.compile("[^0-9\\+\\-\\*\\/()]");
                    Matcher m2 = p2.matcher(str);
                    boolean b2 = m2.find();
                    if (b2) {
                        System.out.println("Проверьте наличия неразрешенных символов");
                        continue;
                    } else

                        // расчет итогов

                        for (int i = 0; i < str.length(); i++) {

                            // если находим открывающуюся скобку, отправляем ее в 'ops'
                            if (str.charAt(i) == '(') {
                                ops.push(str.charAt(i));
                            }

                            // если находим число, отправляем ее в 'values'
                            else if (Character.isDigit(str.charAt(i))) {
                                double val = 0;

                                // в числе может быть больше одной цифры
                                while (i < str.length() &&
                                        Character.isDigit(str.charAt(i))) {
                                    val = (val * 10) + (str.charAt(i) - '0');
                                    i++;
                                }

                                values.push(val);

                                // прямо сейчас i указывает на символ рядом с цифрой,
                                // поскольку цикл for также увеличивает i, мы бы пропустили одну позицию;
                                // нам нужно уменьшить значение i на 1, чтобы исправить смещение.
                                i--;
                            }
                            //Обнаружив закрывающая фигурную скобку, решаем всю фигурную скобку.
                            else if (str.charAt(i) == ')') {
                                while (!ops.empty() && ops.peek() != '(') {
                                    double val2 = values.peek();
                                    values.pop();

                                    double val1 = values.peek();
                                    values.pop();

                                    char op = ops.peek();
                                    ops.pop();

                                    values.push(applyOp(val1, val2, op));
                                }
                                // pop opening brace.
                                if (!ops.empty())
                                    ops.pop();
                            } else {
                                while (!ops.empty() && precedence(ops.peek())
                                        >= precedence(str.charAt(i))) {
                                    double val2 = values.peek();
                                    values.pop();

                                    double val1 = values.peek();
                                    values.pop();

                                    char op = ops.peek();
                                    ops.pop();

                                    values.push(applyOp(val1, val2, op));
                                }
                                //Отправляем текущий оператор в 'ops'.
                                ops.push(str.charAt(i));
                            }
                        }
                    //На данный момент все выражение проанализировано,
                    //примените оставшиеся операции к оставшимся значениям.
                    while (!ops.empty()) {
                        double val2 = values.peek();
                        values.pop();

                        double val1 = values.peek();
                        values.pop();

                        char op = ops.peek();
                        ops.pop();

                        values.push(applyOp(val1, val2, op));
                    }
                    // Верхняя часть «значений» содержит результат, возвращаем его.
                    System.out.println(values.peek());
                    return;
                }
            }
        }

    }

    // функция приоритетов
    public static int precedence(char op) {
        if (op == '+' || op == '-')
            return 1;
        if (op == '*' || op == '/')
            return 2;
        return 0;
    }

    // Функция для выполнения арифметических операций.
    public static double applyOp(double a, double b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }
}
