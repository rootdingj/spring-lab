package com.dj.jvm.bytecode;

public class TestLoadAndStore {

    public static void main(String[] args) {
        TestLoadAndStore test = new TestLoadAndStore();
        test.loadTest(1, 2L, true, new short[1], new Object());
    }

    public void loadTest(int a, long b, boolean flag, short[] arr, Object obj) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(flag);
        System.out.println(arr);
        System.out.println(obj);
    }

    public void storeTest(int a, double b) {
        int m = a + 2;
        long l = 2;
        String str = "jack";
        float f = 10.0F;
        b = 10;
    }
}
