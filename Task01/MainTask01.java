package com.epam.kusanov.Task01;
import static com.epam.kusanov.Task01.Task01.*;

public class MainTask01 {
    public static void main(String[] args) {

//        1. Составить линейную программу, печатающую значение true, если указанное высказывание является истинным,
//        и false — в противном случае:
//        Сумма двух первых цифр заданного четырехзначного числа равна сумме двух его последних цифр.
        func1(1423);

//        2. Вычислить значение выражения по формуле (все переменные принимают действительные значения):
        func2(3,2,3);

//        3. Вычислить периметр и площадь прямоугольного треугольника по длинам а и b  двух катетов.
        func3(5,4);

//        4. Для данных областей составить линейную программу, которая печатает true,
//        если точка с координатами (х, у) принадлежит закрашенной области, и false — в противном случае.
        func4(0,0);

//        5. Даны три действительных числа. Возвести в квадрат те из них, значения которых неотрицательны,
//        и в четвертую степень — отрицательные.
        func5(new double[]{-2, 2, 3});

//        6. Написать программу нахождения суммы большего и меньшего из трех чисел.
        func6(new int[]{-3, 5, 2});

//        7. Составить программу вычисления значений функции  F(x) на отрезке [а, b] с шагом h.
//        Результат представить в виде таблицы, первый столбец которой – значения  аргумента,
//        второй - соответствующие значения функции:
        func7(-2,6,2);

//        8. В массив A [N] занесены натуральные числа. Найти сумму тех элементов, которые кратны данному К.
        int a[] = {1, 2, 3, 4, 8};
        func8(a, 2);

//        9. Заданы два одномерных массива с различным количеством элементов и натуральное число k.
//        Объединить их в один массив, включив второй массив между k-м и (k+1) - м элементами первого.
        int A[] = {1, 2, 3, 4, 8};
        int B[] = {4, 5, 1, 3};
        func9(A, B,2);
//        10. Сформировать квадратную матрицу порядка n по заданному образцу(n - четное):
        int size10 = 6;
        func10(size10);


    }
}