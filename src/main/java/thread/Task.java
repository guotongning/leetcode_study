package main.java.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Task implements Callable<String> {
    private final String name;
    private final int execTime;
    private final ThreadPoolExecutor pool;
    public final List<Task> dependentTasks = new ArrayList<>();

    public Task(String name, int execTime, ThreadPoolExecutor pool) {
        this.name = name;
        this.execTime = execTime;
        this.pool = pool;
    }

    @Override
    public String call() throws Exception {
        List<Future<String>> list = dependentTasks.stream().map(pool::submit).collect(Collectors.toList());
        for (Future<String> future : list) {
            future.get();
        }
        TimeUnit.SECONDS.sleep(execTime);
        System.out.printf("time=%s name=%s thread=%s\r\n", new Date(), name, Thread.currentThread());
        return "done";
    }
}
