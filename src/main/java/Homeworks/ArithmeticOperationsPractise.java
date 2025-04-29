package Homeworks;

public class ArithmeticOperationsPractise {
    public static void main(String[] args) {

        System.out.println("0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int");
        int x = 2;
        int y = 7;
        double floatingResult;

        //Unary operations:
        floatingResult = -x;
        System.out.println("Unary minus. x = " + x + ", result = " + floatingResult + "\n");

        floatingResult = ++x;
        System.out.println("Increment.\nx = " + x + ", result = " + floatingResult);
        floatingResult = x++;
        System.out.println("x = " + x + ", result = " + floatingResult + "\n");

        floatingResult = --x;
        System.out.println("Decrement.\nx = " + x + ", result = " + floatingResult);
        floatingResult = x--;
        System.out.println("x = " + x + ", result = " + floatingResult + "\n");

        // Binary operations:
        floatingResult = x + y;
        System.out.println("Sum.\n" + floatingResult + "\n");

        floatingResult = x - y;
        System.out.println("Subtraction.\n" + floatingResult + "\n");

        x += y;
        floatingResult = x;
        System.out.println("Sum (shortened version).\n" + floatingResult + "\n");

        x -= y;
        floatingResult = x;
        System.out.println("Subtraction (shortened version).\n" + floatingResult + "\n");

        floatingResult = x * y;
        System.out.println("Multiplication.\n" + floatingResult + "\n");

        floatingResult = y / x;
        System.out.println("Integer division (without casting to double).\n" + floatingResult + "\n");

        floatingResult = (double) y / x;
        System.out.println("Double division (with casting to double).\n" + floatingResult + "\n");

        floatingResult = x % y;
        System.out.println("Division remainder.\n" + floatingResult + "\n");
        System.out.println();

        System.out.println("1) применить несколько арифметических операций над int и double в одном выражении");

        int a = 8;
        double b = 2.3;
        int integerResult;

        integerResult = (int) ((a + b) * a);
        System.out.println("Integer expression (with casting to integer).\n" + integerResult + "\n");

        floatingResult = (int) ((a + b) * a);
        System.out.println("Floating expression (with casting to integer).\n" + floatingResult + "\n");

        floatingResult = (a + b) * a;
        System.out.println("Floating expression (without casting).\n" + floatingResult  + "\n");
        System.out.println();

        System.out.println("2) применить несколько логических операций ( < , >, >=, <= )");
        System.out.println("do-while + <=");

        int i = 1;
        do {
            System.out.println(i);
            i++;
        } while (i <= 5);

        System.out.println();
        System.out.println("while + >");

        i = 1;
        while (i < 5) {
            System.out.println(i);
            i++;
        }
        System.out.println();

        System.out.println("3) прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой (какие максимальные и минимальные значения есть, как их получить) и переполнение");
        System.out.println("Byte max value: " + Byte.MAX_VALUE);
        System.out.println("Byte min value: " + Byte.MIN_VALUE);

        System.out.println("Short max value: " + Short.MAX_VALUE);
        System.out.println("Short min value: " + Short.MIN_VALUE);

        System.out.println("Integer max value: " + Integer.MAX_VALUE);
        System.out.println("Integer min value: " + Integer.MIN_VALUE);

        System.out.println("Long max value: " + Long.MAX_VALUE);
        System.out.println("Long min value: " + Long.MIN_VALUE);

        System.out.println("Float max value: " + Float.MAX_VALUE);
        System.out.println("Float min value: " + Float.MIN_VALUE);

        System.out.println("Double max value: " + Double.MAX_VALUE);
        System.out.println("Double min value: " + Double.MIN_VALUE);

        System.out.println();

        System.out.println("4) получить переполнение при арифметической операции");
        System.out.println(Float.MAX_VALUE + 10_000);
        System.out.println(Integer.MIN_VALUE - 10_000);
    }
}