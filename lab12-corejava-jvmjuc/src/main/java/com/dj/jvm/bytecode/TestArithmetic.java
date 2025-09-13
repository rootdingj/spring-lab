package com.dj.jvm.bytecode;

public class TestArithmetic {

    public static void main(String[] args) {

    }

    public void naNTest(){
        int i = 10;
        double j = i / 0.0;
        System.out.println(j);// Infinity:无穷大

        double d1 = 0.0;
        double d2 = d1 / 0.0;
        System.out.println(d2);// NaN: not a number

        int c = 10;
        int c1 = c / 0;
        System.out.println(c1);// java.lang.ArithmeticException: / by zero
    }
}
