package com.zp.integration.common.base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 非公平锁测试用例
 */
public class NoFairLockTest {

    public static void main(String[] args) {

        // create NonfairLock
        ReentrantLock lock = new ReentrantLock(false);

        try{
            // locked
            lock.lock();

            // simulate business process time
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // release lock
            lock.unlock();
        }
    }
}
