package com.dj.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用读写锁实现的一个缓存系统，读的时候可以并发执行，当缓存中没有数据时，要到数据库中查询数据. 此时只能写数据，不能读数据。当完数据之后，又可以并发地读取数据。
 */
public class ReadWriteLockCache {

    // 定义一个map用来存放要缓存起来的数据
    private Map<String, Object> cache = new HashMap<String, Object>();

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    // 该方法中，读数据可以并发地读取，写数据与读数据，写数据与写数据之间不能并发地运行
    public Object getData(String key) {
        // 刚进来的时候，上一把写锁
        rwLock.readLock().lock();
        Object obj = null;
        try {
            obj = cache.get(key);
            if (obj == null) {
                // 如果数据为空，则需要到数据库中查询数据，这时候把读锁释放掉，上一把写锁，不能同时写数据
                // 在上写锁之前，首先要把读锁释放掉
                rwLock.readLock().unlock();
                rwLock.writeLock().lock();
                // 查询数据库的代码 TODO
                try {
                    // 必须重新检查obj是否为空，因为这时候，另外一个线程可能会获得写锁，从而让obj有值
                    if (obj == null) {
                        obj = "查询数据库得到的数据";
                    }
                    cache.put(key, obj);
                } finally {
                    rwLock.writeLock().unlock();
                }
                // 因为前面释放了写锁，所以这里要把写锁重新锁上
                rwLock.readLock().lock();
            }
        } finally {
            rwLock.readLock().unlock();
        }
        return obj;
    }

}
