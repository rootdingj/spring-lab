 package com.dj.jvm.classLoader;

 public class TestClient {

    public static void main(String[] args) throws Exception {
        CustomClassLoader loader = new CustomClassLoader();
        loader.setBasePath("D:\\project\\javaDemo\\corejava-jvmjuc\\src\\main\\java\\com\\dj\\jvm\\classLoader\\");
        Class<?> userClass = loader.findClass("com.dj.corejava.jvm.classLoader.User");
        System.out.println(userClass.getClassLoader());
        Object instance = userClass.newInstance();
        System.out.println(instance);
    }

}
