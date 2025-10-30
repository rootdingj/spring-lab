package com.dj.zk;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * @Auther: steven
 * @Date: 2020/08/28
 * @Description: ACL 权限控制，用户名:密码认证策略生成器
 */
public class GenerateDigest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String digest = DigestAuthenticationProvider.generateDigest("super:admin");
        System.out.println("digest="+digest);
    }

}
