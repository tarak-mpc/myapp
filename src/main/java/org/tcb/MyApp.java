package org.tcb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class MyApp {
    private static final int MAX_NUMBER_THREADS = 1;

    public static void main(String[] args ) throws IOException {

//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//
//        KafkaDataConsumeLoop consumer = new KafkaDataConsumeLoop();
//        executor.execute(consumer);

        Properties props = new Properties();
        InputStream input = null;

        input = new FileInputStream("src/main/resources/configA.properties");

        props.load(input);

        String topic = props.getProperty("topic");
        String groupId = props.getProperty("groupId");
        String hbaseTableName = props.getProperty("hbaseTableName");
        String hbaseColumnFamilyName = props.getProperty("hbaseColumnFamilyName");
        String esIndex = props.getProperty("esIndex");
        String schemaFile = props.getProperty("schemaFile");

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_NUMBER_THREADS);
        executorService.submit(new KafkaDataConsumeLoop(topic, groupId, hbaseTableName, hbaseColumnFamilyName, esIndex, schemaFile));
        executorService.shutdown();
    }
}
