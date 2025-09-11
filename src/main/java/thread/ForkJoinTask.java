package main.java.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ForkJoinTask extends RecursiveTask<String> {
    private final String name;
    private final int execTime;
    public final List<ForkJoinTask> dependentTasks = new ArrayList<>();

    public ForkJoinTask(String name, int execTime) {
        this.name = name;
        this.execTime = execTime;
    }

    @Override
    protected String compute() {
        for (ForkJoinTask t : dependentTasks) {
            t.fork();
        }
        for (ForkJoinTask t : dependentTasks) {
            t.join();
        }
        try {
            TimeUnit.SECONDS.sleep(execTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("time=%s name=%s thread=%s\r\n", new Date(), name, Thread.currentThread());
        return "done";
    }
}
