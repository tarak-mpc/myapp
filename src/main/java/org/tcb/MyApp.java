package org.tcb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;




public class MyApp {
    private static final int MAX_NUMBER_THREADS = 5;

    public static void main(String[] args ){

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        KafkaDataConsumeLoop consumer = new KafkaDataConsumeLoop();
        executor.execute(consumer);

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_NUMBER_THREADS);
        executorService.submit(new KafkaDataConsumeLoop());
        executorService.shutdown();
    }
}
