package com.zp.integration.common.base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


/**
 * 如果为了可取消性而使用 Future 但又不提供可用的结果，
 * 则可以声明 Future<?> 形式类型、并返回 null 作为底层任务的结果。
 */

@Slf4j
public class CallableAndFutureTask {
    public static void main(String[] args) {
        // 第一种方式
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executorService.submit(futureTask);
        executorService.shutdown();

        // 第二种方式：注意这种方式和第一种方式效果是类似的，只不过一个使用的是 ExecutorService，一个使用的是 Thread
//        Task task1 = new Task();
//        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(task1);
//        Thread thread = new Thread(futureTask1);
//        thread.start();

        try {
            log.info("主线程打了一会儿盹儿...");
            Thread.sleep(1000);
            System.out.println("主线程打了一会儿盹儿...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行任务...");

        try {
            log.info("task 运行结果为：" + futureTask.get() + 1);
            System.out.println("task 运行结果为：" + futureTask.get() + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕！！！");
    }
}
