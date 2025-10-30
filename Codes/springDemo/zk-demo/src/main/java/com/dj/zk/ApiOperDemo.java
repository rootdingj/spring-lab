package com.dj.zk;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

/**
 * @Auther: steven
 * @Date: 2020/08/28
 * @Description: zk 原生 api 练习
 */
public class ApiOperDemo {

    private final static String CONNECTION_URL = "47.106.125.36:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat = new Stat();

    /**
     *    创建 zk session连接
     * 
     * @throws IOException
     * @throws InterruptedException
     */
    public static ZooKeeper createSession() throws IOException, InterruptedException {

        ZooKeeper zk = new ZooKeeper(CONNECTION_URL, 5000, new Watcher() {

            // 一次性事件监听，当节点数据发生变化会监听到
            @Override
            public void process(WatchedEvent event) {
                // 如果当前的连接状态是连接成功的，那么通过计数器去控制
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                    System.out.println("state:" + event.getState());
                }
            }
        });
        countDownLatch.await();
        System.out.println("创建连接成功，state：" + zk.getState());
        return zk;
    }

    /**
     * api 操作
     * @param zk
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void apiOper(ZooKeeper zk) throws KeeperException, InterruptedException {
        if (zk != null && States.CONNECTED.equals(zk.getState())) {
            // 创建节点（节点路径，节点数据，节点acl权限，节点类型）
            String node =
                zk.create("/apiNode", "apiData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("创建节点 node：" + node);
            // 获取节点
            byte[] data = zk.getData("/apiNode", true, stat);
            System.out.println("获取节点 stat:" + stat + ", data:" + new String(data));

            // 修改节点
//            Stat setstat = zk.setData("/apiNode", "apiData".getBytes(), -1);
//            Thread.sleep(2000);
//            System.out.println("修改节点 stat：" + setstat);

            // 删除节点
//            zk.delete("/apiNode", -1);
//            Thread.sleep(2000);

            // 获取指定节点下的子节点
//            List<String> childrens = zk.getChildren("/apiData", true);
//            System.out.println(childrens);
        }
    }
    
    /**
     * 权限控制
     * @param zk
     * @throws NoSuchAlgorithmException 
     * @throws InterruptedException 
     * @throws KeeperException 
     */
    public static void authOper(ZooKeeper zk) throws NoSuchAlgorithmException, KeeperException, InterruptedException {
        // 创建 ACL列表
        ACL acl = new ACL(ZooDefs.Perms.ALL, new Id("digest", DigestAuthenticationProvider.generateDigest("root:root")));
        ACL acl2 = new ACL(ZooDefs.Perms.CREATE, new Id("ip", CONNECTION_URL));
        List<ACL> acls = new ArrayList<>();
        acls.add(acl);
        acls.add(acl2);
        // 创建节点并自定义acl权限
        String node = zk.create("/auth", "123".getBytes(), acls, CreateMode.PERSISTENT);
        System.out.println("创建节点 node：" + node);
        zk.addAuthInfo("digest", "root:root".getBytes());
        zk.create("/auth/auth-1", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        // 获取节点
        byte[] data = zk.getData("/auth", true, stat);
        System.out.println("获取节点 stat:" + stat + ", data:" + new String(data));

    }

    public static void main(String[] args) {
        try {
            ZooKeeper zk = createSession();
//            apiOper(zk);
            authOper(zk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
