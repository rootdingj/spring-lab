package com.dj.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static AtomicReference<User> atomUserRef = new AtomicReference<User>();

    public static void main(String[] args) {
           User user = new User("air",18);
           atomUserRef.set(user);
           System.out.println("atomUserRef compareAndSet before:"+atomUserRef.get().toString());
           User updateUser = new User("cow",20);
           atomUserRef.compareAndSet(user, updateUser);
           System.out.println("atomUserRef compareAndSet after:"+atomUserRef.get().toString());
    }

    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User [name=" + name + ", age=" + age + "]";
        }
    }

}
