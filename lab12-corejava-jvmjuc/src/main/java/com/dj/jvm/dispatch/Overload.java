package com.dj.jvm.dispatch;

import java.io.Serializable;

/**
 * 重载方法优先级匹配测试
 * 
 * @author Steven
 * @date 2020/08/04
 */
public class Overload {

    // 可变参数优先级最低，'a' -- 数组
    public static void sayHello(char... arg) {
        System.out.println("hello char...");
    }

    // 'a' -- Character -- Object，Object顶级父类，离的越远优先级越低
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    // 'a' -- Character -- Serializable，发生了1次自动装箱和1次自动类型转换
    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    // 'a' -- Character，发生了1次自动装箱
    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    // 'a' -- 97 -- 97L，发生2次自动类型转换(char<int<long<float<double)
    public static void sayHello(long arg) {
        System.out.println("hello long");
    }

    // 'a' 可表示为数字97，发生1次自动类型转换
    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    // 'a'
    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    public static void main(String[] args) {
        // 控制台输出谁就注释掉谁进行测试
        sayHello('a');
    }

}
