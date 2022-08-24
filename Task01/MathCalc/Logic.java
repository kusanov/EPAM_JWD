package com.epam.kusanov.Task01.MathCalc;

public class Logic {

    public static double logic2(int a, int b, int c) {
        System.out.println("Задание №2");
        double z = (b + Math.sqrt(Math.pow(b, 2) + 4 * a * c)) / (2 * a)
                + Math.pow(a, 3) * c
                + Math.pow(b, -2);
        return z;
    }

    public static boolean logic4(double x, double y) {
        System.out.println("Задание №4");
        return ((x >= -1 && x <= 1 && y >= 0 && y <= 2) ||
                (x >= -2 && x <= 2 && y >= -1.5 && y <= 0));
        }
    public static int logic8(int a, int b, int K) {
        System.out.println("Задание №8");
        int [] A = new int[]{a,b};
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % K == 0) {
                sum = sum + A[i];
            }
        }
        return sum;
    }
}
