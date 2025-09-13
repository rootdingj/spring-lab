package com.dj.jvm.dispatch;

/**
 * 动态分派测试
 * 
 * @author Steven
 * @date 2020/08/04
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("hello,gentleman");  
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("hello,lady");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }

}
