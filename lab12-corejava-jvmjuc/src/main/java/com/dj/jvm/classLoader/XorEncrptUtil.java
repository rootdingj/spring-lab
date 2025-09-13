package com.dj.jvm.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 加解密工具类
 * 
 * @author Steven
 * @date 2020/08/03
 */
public class XorEncrptUtil {

    /**
     * 异或操作
     * 
     * @param in
     * @param out
     * @throws IOException
     */
    private static void xor(InputStream in, OutputStream out) throws IOException {
        int ch = 0;
        while (-1 != (ch = in.read())) {
            ch = ch ^ 0xff;
            out.write(ch);
        }
    }

    /**
     * 加密
     * 
     * @param src 源文件
     * @param des 目标文件
     */
    public static void encrypt(File src, File des) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(des);
            xor(in, out);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解密
     * 
     * @param src 源文件
     * @return
     */
    public static byte[] decrypt(File src) {
        InputStream in = null;
        ByteArrayOutputStream bos = null;
        try {
            in = new FileInputStream(src);
            bos = new ByteArrayOutputStream();
            xor(in, bos);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        File src = new File("D:\\resource\\git-steven\\corejava\\corejava-jvm\\target\\classes\\com\\dj\\corejava\\jvm\\classLoader\\UserSrc.class");
        File des = new File("D:\\resource\\git-steven\\corejava\\corejava-jvm\\target\\classes\\com\\dj\\corejava\\jvm\\classLoader\\User.class");
        XorEncrptUtil.encrypt(src, des);
        System.out.println("加密完成！");
    }

}
