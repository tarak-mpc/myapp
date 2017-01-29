package org.tcb;


import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.tcb.dao.ElasticsearchDAO;
import org.tcb.dao.HbaseDAO;


import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class KafkaDataConsumeLoop implements Runnable {
    private final KafkaConsumer<String, GenericRecord> consumer;
    private final AtomicBoolean shutdown;
    private final CountDownLatch shutdownLatch;
    private final String topic;
    private final HbaseDAO hbaseDao;
    private final ElasticsearchDAO elasticsearchDAO;
    private final String hbaseColumnFamilyName;
    private final String hbaseTableName;
    private final String esIndex;
    private final String schemaFile;
    private List<String>  fieldNames;

    public KafkaDataConsumeLoop(String topic, String groupId, String hbaseColumnFamilyName, String hbaseTableName, String esIndex, String schemaFile) {
        Properties props = new Properties();

        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9090,kafka1:9091,kafka2:9092");
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        this.consumer = new KafkaConsumer<>(props);
        this.topic = topic;
        this.shutdown = new AtomicBoolean(false);
        this.shutdownLatch = new CountDownLatch(1);

        this.hbaseDao = SingletonInstance.INSTANCE.getHbaseDAO();
        this.elasticsearchDAO = SingletonInstance.INSTANCE.getElasticsearchDAO();

        this.hbaseColumnFamilyName = hbaseColumnFamilyName;
        this.hbaseTableName = hbaseTableName ;

        this.esIndex = esIndex ;
        this.schemaFile = schemaFile ;
    }

    public void process(ConsumerRecord<String, GenericRecord> record) {
        String id = elasticsearchDAO.createIndex(esIndex,record.value().toString());
        for (String name : fieldNames) {
//            System.out.println(name + " : " + record.value().get(name));
            hbaseDao.save(hbaseTableName, hbaseColumnFamilyName, name, "row" + Integer.toString((int) record.offset()), String.valueOf(record.value().get(name)));
        }
    }

    public void run() {
        try {
            consumer.subscribe(Collections.singletonList(topic));
            Schema schema = new Schema.Parser().parse(schemaFile);
            this.fieldNames = schema.getFields().stream().map(Schema.Field::name).collect(Collectors.toList());

            while (!shutdown.get()) {
                //System.out.println("Listening for record.");
                ConsumerRecords<String, GenericRecord> records = consumer.poll(1000);
                System.out.println(System.currentTimeMillis() + "  --  waiting for data...");
                //System.out.println("Processing record.");
                records.forEach(this::process);
                //System.out.println("Finished.");
                consumer.commitSync();

            }
        } finally {
            consumer.close();
            hbaseDao.closeConnection();
            elasticsearchDAO.shutdown();
            shutdownLatch.countDown();
        }
    }

    public void shutdown() throws InterruptedException {
        shutdown.set(true);
        shutdownLatch.await();
    }
}


