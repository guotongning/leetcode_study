package main.java.thread;

import java.util.Date;
import java.util.concurrent.*;

public class ForkJoinPoolDemo {

    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 4, 50, TimeUnit.SECONDS,
//            new SynchronousQueue<>(),
            new ArrayBlockingQueue<>(100),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private static final ForkJoinPool forkjoinPool = new ForkJoinPool(4);

    public static void main(String[] args) {
//        testThreadPoolExecutor();
        testForkJoinPool();
    }

    private static void testForkJoinPool() {
        ForkJoinTask t1 = new ForkJoinTask("t1", 1);
        ForkJoinTask t2 = new ForkJoinTask("t2", 1);
        ForkJoinTask t3 = new ForkJoinTask("t3", 1);
        ForkJoinTask t4 = new ForkJoinTask("t4", 1);
        ForkJoinTask t5 = new ForkJoinTask("t5", 10);
        ForkJoinTask t6 = new ForkJoinTask("t6", 10);
        ForkJoinTask t7 = new ForkJoinTask("t7", 1);
        ForkJoinTask t8 = new ForkJoinTask("t8", 1);
        ForkJoinTask t9 = new ForkJoinTask("t9", 1);

        t1.dependentTasks.add(t2);
        t1.dependentTasks.add(t3);
        t1.dependentTasks.add(t4);
        t1.dependentTasks.add(t5);
        t1.dependentTasks.add(t6);

        t2.dependentTasks.add(t7);
        t3.dependentTasks.add(t8);
        t4.dependentTasks.add(t9);
        System.out.printf("start time = %s\r\n", new Date());
        forkjoinPool.invoke(t1);
    }

    private static void testThreadPoolExecutor() {
        Task t1 = new Task("task1", 1, pool);
        Task t2 = new Task("task2", 1, pool);
        Task t3 = new Task("task3", 1, pool);
        Task t4 = new Task("task4", 1, pool);
        Task t5 = new Task("task5", 10, pool);
        Task t6 = new Task("task6", 10, pool);
        Task t7 = new Task("task7", 1, pool);
        Task t8 = new Task("task8", 1, pool);
        Task t9 = new Task("task9", 1, pool);


        t1.dependentTasks.add(t2);
        t1.dependentTasks.add(t3);
        t1.dependentTasks.add(t4);
        t1.dependentTasks.add(t5);
        t1.dependentTasks.add(t6);

        t2.dependentTasks.add(t7);
        t3.dependentTasks.add(t8);
        t4.dependentTasks.add(t9);
        System.out.printf("start time = %s\r\n", new Date());
        pool.submit(t1);
    }
}
