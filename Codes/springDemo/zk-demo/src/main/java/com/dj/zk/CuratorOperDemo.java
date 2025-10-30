package com.dj.zk;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @Auther: steven
 * @Date: 2020/08/28
 * @Description: Curator 框架练习
 */
public class CuratorOperDemo {

    private final static String CONNECTION_URL = "47.106.125.36:2181";

    private static CuratorFramework curator;

    private static Stat stat;

    public static CuratorFramework getInstance() {
        if (curator == null) {
            // 两种创建 CuratorFramework 的方式
//             curator = CuratorFrameworkFactory.newClient(CONNECTION_URL, 5000, 5000,
//                     new ExponentialBackoffRetry(1000,3));

            // fluent风格
            curator = CuratorFrameworkFactory.builder().connectString(CONNECTION_URL).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

            curator.start();
            System.out.println("创建 CuratorFramework 成功！");
        }
        return curator;
    }

    public static void curatorOper() throws Exception {
        CuratorFramework curator = getInstance();
        // 级联创建节点
         String node = curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
         .forPath("/curator/curator1/curator11", "curatorData11".getBytes());
         System.out.println("级联创建节点:" + node);

        // 删除节点，默认情况下，version为-1
         curator.delete().deletingChildrenIfNeeded().forPath("/curator");

        // 修改节点数据
//        stat = curator.setData().forPath("/curator/curator1/curator11", "curatorData12".getBytes());
//        System.out.println("修改节点数据:" + stat);

        // 查询节点
        stat = new Stat();
        byte[] bytes = curator.getData().storingStatIn(stat).forPath("/curator");
        System.out.println(new String(bytes) + "-->stat:" + stat);

        // 异步操作
        /*ExecutorService service = Executors.newFixedThreadPool(1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent)
                        throws Exception {
                        System.out.println(Thread.currentThread().getName() + "->resultCode:"
                            + curatorEvent.getResultCode() + "->" + curatorEvent.getType());
                        countDownLatch.countDown();
                    }
                }, service).forPath("/curator", "curator".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        service.shutdown();*/

        // 事物操作
//        Collection<CuratorTransactionResult> results = curator.inTransaction().create()
//            .forPath("/curator/curator1/curator11", "curatorData".getBytes()).and().setData().forPath("/curator/curator1/curator11", "curatorData111".getBytes()).and().commit();
//        for (CuratorTransactionResult result : results) {
//            System.out.println(result.getForPath() + "->" + result.getType());
//        }

    }

    public static void main(String[] args) throws Exception {
        CuratorFramework instance = getInstance();
        curatorOper();
    }

}
