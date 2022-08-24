package com.epam.kusanov.Task01;

import java.util.Arrays;

public class Task01 {

    static void func1(int x1) {
        System.out.println("Задание №1");
        System.out.println(x1 / 1000 + x1 / 100 % 10 == x1 / 10 % 10 + x1 % 10);
    }

    static void func2(double a, double b, double c) {
        System.out.println("Задание №2");
        double z = (b + Math.sqrt(Math.pow(b, 2) + 4 * a * c)) / (2 * a)
                + Math.pow(a, 3) * c
                + Math.pow(b, -2);
        System.out.println(z);
    }

    static void func3(int a3, int b3) {
        System.out.println("Задание №3");
        System.out.println("Периметр прямоугольного треугольника: "
                + (a3 + b3 + Math.sqrt(Math.pow(a3, 2) + Math.pow(b3, 2))));
        System.out.println("Площадь прямоугольного треугольника: " + a3 * b3 / 2);
    }

    static void func4(double x, double y) {
        System.out.println("Задание №4");
        System.out.println(((x >= -1 && x <= 1 && y >= 0 && y <= 2) ||
                (x >= -2 && x <= 2 && y >= -1.5 && y <= 0)));
    }

    static void func5(double[] array5) {
        System.out.println("Задание №5");
        System.out.println("Начальный массив:" + Arrays.toString(array5));
        for (int i = 0; i < array5.length; i++) {
            if (array5[i] < 0) {
                array5[i] = Math.pow(array5[i], 4);
            } else array5[i] = Math.pow(array5[i], 2);
        }
        System.out.println("Итоговый массив:" + Arrays.toString(array5));
    }

    static void func6(int[] array6) {
        System.out.println("Задание №6");
        int min = 0;
        int max = 0;
        System.out.println("Начальный массив:" + Arrays.toString(array6));
        for (int i = 0; i < array6.length; i++) {
            if (array6[i] < min)
                min = array6[i];
            if (array6[i] > max) {
                max = array6[i];
            }
        }
        System.out.println("Сумма большего и меньшего из трех чисел: " + (min + max));
    }

    static void func7(int a, int b, int h) {
        System.out.println("Задание №7");
        int x;
        double y;
        for (x = a; x <= b; x += h) {
            y = Math.pow(Math.sin(x), 2) - Math.cos(2 * x);
            System.out.println("x=" + x + " y=" + y);
        }
    }

    static void func8(int A[], int K) {
        System.out.println("Задание №8");
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % K == 0) {
                sum = sum + A[i];
            }
        }
        System.out.println(sum);
    }

    static void func9(int[] array1, int[] array2, int k) {
        System.out.println("Задание №9");
        System.out.println("Первый массив: " + Arrays.toString(array1));
        System.out.println("Второй массив: " + Arrays.toString(array2));
        System.out.println("k = " + k);

        int[] arrayA = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, arrayA, 0, k + 1);
        System.arraycopy(array2, 0, arrayA, k + 1, array2.length);
        System.arraycopy(array1, k + 1, arrayA, k + array2.length + 1, array1.length - k - 1);

        System.out.println("Новый массив: " + Arrays.toString(arrayA));
    }

    static void func10(int size) {
        System.out.println("Задание №10");
        int[][] matrix = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i % 2 != 0) {
                    System.out.print((matrix[i][j] = (size - j)) + " ");//вывод четной строки
                } else {
                    System.out.print((matrix[i][j] = (j + 1)) + " ");//вывод нечетной строки
                }
            }
            System.out.println();
        }
    }
}
