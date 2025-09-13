 package com.dj.jvm.dispatch;

 /**
  * 静态分派测试
  * @author Steven
  * @date 2020/08/04
  */
 public class StaticDispatch {
     
     static abstract class Human {}
     
     static class Man extends Human {}
     
     static class Woman extends Human {}
     
     public void sayHello(Human guy) {
         System.out.println("hello,guy");
     }
     
     public void sayHello(Man guy) {
         System.out.println("hello,gentleman");
     }
     
     public void sayHello(Woman guy) {
         System.out.println("hello,lady");
     }

    public static void main(String[] args) {
        // 测试1
//        Human man = new Man();
//        Human woman = new Woman();
        // 测试2
        Man man = new Man();
        Woman woman = new Woman();
        
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }

}
