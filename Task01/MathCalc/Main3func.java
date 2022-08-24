package com.epam.kusanov.Task01.MathCalc;

import static com.epam.kusanov.Task01.MathCalc.Logic.*;
import static com.epam.kusanov.Task01.MathCalc.Output.*;

public class Main3func {
    public static void main(String[] args) {

        Input input1 = new Input();
        int in1 = input1.inputInt();
        Input input2 = new Input();
        int in2 = input2.inputInt();
        Input input3 = new Input();
        int in3 = input3.inputInt();
        Input input4 = new Input();
        double in4 = input4.inputDouble();
        Input input5 = new Input();
        double in5 = input5.inputDouble();
        outputDouble(logic2(in1, in2, in3));
        outputBoolean(logic4(in4, in5));
        outputInt(logic8(in1, in2, in3));
    }
}