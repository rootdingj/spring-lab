package com.dj.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @Auther: steven
 * @Date: 2020/08/28
 * @Description: watcher 事件机制
 */
public class WatcherDemo implements Watcher {

    private final static String CONNECTION_URL = "47.106.125.36:2181";
    static ZooKeeper zooKeeper;

    static {
        try {
            zooKeeper = new ZooKeeper(CONNECTION_URL, 4000, new WatcherDemo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("eventType:" + event.getType());
        // 当节点数据发生变化
        if (event.getType() == Event.EventType.NodeDataChanged) {
            try {
                Stat stat = zooKeeper.exists(event.getPath(), true);
                System.out.println("process stat:" + stat);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "/watcher";
        if (zooKeeper.exists(path, false) == null) {
            zooKeeper.create(path, "watcherData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        Thread.sleep(1000);
        // true 表示使用 zookeeper 实例中配置的 watcher
        Stat stat = zooKeeper.exists(path, true);
        System.out.println("main stat:" + stat);
        System.in.read();
    }

}
