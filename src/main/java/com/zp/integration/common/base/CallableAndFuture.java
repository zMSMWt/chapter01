package com.zp.integration.common.base;

import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();

        /**
         * 改进：
         * Future<Integer> futureTask = null;
         * try{
         *      futureTask = executor.submit(task);
         *      result = futureTask.get(time,timeUnit);
         * }catch(RejectedExecutionException re){
         *      //处理进入线程池失败的情况，也即，不仅获取线程资源失败，并且由于等待队列已满，甚至无法进入队列直接失败。
         * }
         *
         * 为什么要这么做？
         * 1. 因为如果当前线程池已经满，且排队的队列已满的情况下，会直接拒绝 submit,
         *    这个时候如果使用之前的写法，会由于无法得知 futurnTask 是否成功提交，
         *    如果这个时候，直接使用 get() 方法，会由于任务没有实际提交，
         *    导致主线程等待一个不可能出现的结果，造成线程挂起。所以需要处理拒绝请求的异常情况。
         * 2. 一般来说，子线程不一定是安全的，特别实在 web 场景，
         *    很多时候，依赖方可能是有问题，为了尽可能减少堵塞的线程资源，对子线程做有限制的等待，一般来说是有必要的。
         *
         * 继续改进：
         * try {
         *      futureTask = executor.submit(task);
         *      result = futureTask.get(time,timeUnit);
         * } catch (Exception e) {
         *      if(e instanceof InterruptedException){
         *          //中端异常处理
         *      }else if(e instanceof RejectedExecutionException){
         *          //提交线程池被拒绝异常处理
         *      }else if(e instanceof ExecutionException){
         *          //线程执行异常处理
         *      }else if(e instanceof TimeoutException){
         *          //等待结果超时异常处理
         *      }else{}
         * }
         */

        try {
            Thread.sleep(1000);
            System.out.println("主线程打了一会儿盹儿...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行任务...");

        try {
            System.out.println("task 运行结果为：" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕！！！");
    }
}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在此计算...");
        Thread.sleep(3000);
        System.out.println("子线程打了一会盹儿...");
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}