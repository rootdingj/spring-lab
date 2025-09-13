package com.dj.jvm.classLoader;

import java.io.File;

/**
 * 自定义类加载器
 * 
 * @author Steven
 * @date 2020/08/03
 */
public class CustomClassLoader extends ClassLoader {

    private String basePath;
    private final static String FILE_EXT = ".class";

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    // 实际解密
    private byte[] loadClassData(String name) {
        byte[] data = null;
        try {
            String tempName = name.replaceAll("\\.", "\\\\");
            data = XorEncrptUtil.decrypt(new File(basePath + tempName + FILE_EXT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

}
