package com.dj.jvm.bytecode;

public class TestObjectAndMethod {

    /**
     * 数组测试
     */
    public void arrTest() {
        int[] intArray = new int[10];
        intArray[3] = 20;
        System.out.println(intArray[1]);
    }

    /***
     * 类型检查指令
     */
    public String checkCastTest(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        } else {
            return null;
        }
    }

}
