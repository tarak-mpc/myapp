package org.tcb;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;


public class MyApp {
    private static final int MAX_NUMBER_THREADS = 3;
    private static ExecutorService executorService;

    public static void main(String[] args) throws IOException {


        try(Stream<Path> paths = Files.walk(Paths.get("src/main/resources"))) {
            paths.forEach(filePath -> {

                if (Files.isRegularFile(filePath) && filePath.getFileName().toString().startsWith("config")) {
                    System.out.println(filePath);
                    Properties props = new Properties();

                    try {
                        props.load(new FileInputStream(filePath.toFile()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String topic = props.getProperty("topic");
                    String groupId = props.getProperty("groupId");
                    String hbaseTableName = props.getProperty("hbaseTableName");
                    String hbaseColumnFamilyName = props.getProperty("hbaseColumnFamilyName");
                    String esIndex = props.getProperty("esIndex");
                    String schemaFile = props.getProperty("schemaFile");


                    executorService = Executors.newFixedThreadPool(MAX_NUMBER_THREADS);
                    executorService.execute(new KafkaDataConsumeLoop(topic, groupId, hbaseTableName, hbaseColumnFamilyName, esIndex, schemaFile));

                }
            });
        }
        executorService.shutdown();
    }
}
