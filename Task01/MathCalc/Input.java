package com.epam.kusanov.Task01.MathCalc;

import java.util.Scanner;

public class Input {

    public Input() {
    }

    public int inputInt (){
        System.out.println("Введите целое число: ");
        Scanner scannerInt = new Scanner(System.in);
        int x = scannerInt.nextInt();
        return x;
    }
    public double inputDouble (){
        System.out.println("Введите любое число: ");
        Scanner scannerInt = new Scanner(System.in);
        double y = scannerInt.nextDouble();
        return y;
    }
}
