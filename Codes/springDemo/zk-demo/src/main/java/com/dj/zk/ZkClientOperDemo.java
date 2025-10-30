package com.dj.zk;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * @Auther: steven
 * @Date: 2020/08/28
 * @Description: ZkClient 练习
 */
public class ZkClientOperDemo {

    private final static String CONNECTION_URL = "47.106.125.36:2181";

    /**
     * 获取 ZkClient 实例
     * 
     * @return
     */
    private static ZkClient getInstance() {
        return new ZkClient(CONNECTION_URL, 10000);
    }

    /**
     * watcher 监听器
     * 
     * @param zkClient
     */
    public static void zkClientListener(ZkClient zkClient, String path) {
        // 监听节点数据的变化
        zkClient.subscribeDataChanges(path, new IZkDataListener() {

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("监听节点删除，节点名称：" + dataPath);
            }

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

                System.out.println("监听节点数据变化，节点名称：" + dataPath + "->节点修改后的值" + data);
            }
        });

        // 监听子节点变化
        zkClient.subscribeChildChanges(path, new IZkChildListener() {

            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("监听子节点变化，节点名称：" + parentPath + "->" + "当前的节点列表：" + currentChilds);
            }
        });
    }

    /**
     * ZkClient api 测试
     * 
     * @throws InterruptedException
     */
    public static void zkClientOper() throws InterruptedException {
        ZkClient zkClient = getInstance();
        zkClientListener(zkClient, "/zkClient");
        // 级联创建节点
        zkClient.createPersistent("/zkClient/zkNode2/zkNode21", true);
        System.out.println("级联创建节点成功！");
        // 获取子节点
        List<String> list = zkClient.getChildren("/zkClient");
        System.out.println("获取zkClient的子节点" + list);

        // 删除节点
        zkClient.deleteRecursive("/zkClient");

        // 设值节点数据
        zkClient.writeData("/zkClient/zkNode2/zkNode21", "data21");
        TimeUnit.SECONDS.sleep(1);

        zkClient.delete("/zkClient/zkNode2/zkNode21");
        TimeUnit.SECONDS.sleep(1);

        zkClientListener(zkClient, "/zkClient");
    }

    public static void main(String[] args) {
        try {
            zkClientOper();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
