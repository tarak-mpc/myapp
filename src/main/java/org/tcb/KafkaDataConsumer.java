package org.tcb;


import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import kafka.utils.VerifiableProperties;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;





import org.tcb.avro.type_a;
import org.tcb.avro.type_b;
import org.tcb.avro.type_c;
import org.tcb.dao.HbaseDAO;

import static io.confluent.shaded.org.joda.time.format.ISODateTimeFormat.time;


//        import hbaseAdo.HbaseDAO;


public class KafkaDataConsumer {


    private static KafkaDataConsumer toHbaseConsumer;
    private KafkaConsumer<String, type_a> consumer;




    protected String topic;
    protected Properties kafkaProps;

    ///
//    protected HbaseDAO hbaseDao;
//    protected String hbaseTableName;
//    protected String hbaseColumnFamilyName;
//    protected String hbaseColumnName;
    ////


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // currently hardcoding a lot of parameters, for simplicity
        String groupId = "reader4";
        String topic = "testa";
        String url = "http://schema-registry:8081";
        String brokers = "kafka0:9090,kafka1:9091,kafka2:9092";

        toHbaseConsumer = new KafkaDataConsumer().ConsumerBuilder(brokers, topic, groupId, url);
        toHbaseConsumer.startReading();



    }





    public void startReading() throws ExecutionException, InterruptedException {

        consumer = new KafkaConsumer<>(kafkaProps);
        consumer.subscribe(Collections.singletonList(topic));

        System.out.println("Reading topic:" + topic);


        while (true) {
            ConsumerRecords<String, type_a> records = consumer.poll(1000);
            System.out.println(System.currentTimeMillis() + "  --  waiting for data...");
            for (ConsumerRecord<String, type_a> record : records) {
                System.out.printf("offset = %d\n", record.offset());

                String type = record.key();
                type_a data = record.value();


                System.out.println(type_a.getClassSchema().getFields());


                ///
//                String valueHbaseString = hbaseDao.get(hbaseTableName,hbaseColumnFamilyName,hbaseColumnName, id);
//                int valueHbase = valueHbaseString == "" ? 0 : Integer.parseInt(valueHbaseString);
//                int valueUpdated = valueHbase + Integer.parseInt(value);
//                hbaseDao.save(hbaseTableName,hbaseColumnFamilyName,hbaseColumnName, id, String.valueOf(valueUpdated));
                ////
            }
            consumer.commitSync();
        }

//        final Thread mainThread = Thread.currentThread();
//
//        // Registering a shutdown hook so we can exit cleanly
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            public void run() {
//                System.out.println("Starting exit...");
//                // Note that shutdownhook runs in a separate thread, so the only
//                // thing we can safely do to a consumer is wake it up
//                consumer.wakeup();
//                try {
//                    mainThread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }

//    public void close(){
//        this.consumer.close();
////        this.hbaseDao.closeConnection();
//    }

    public static KafkaDataConsumer ConsumerBuilder(String brokerServer, String topic, String groupId, String url){
        KafkaDataConsumer kafkaConsumer = new KafkaDataConsumer();
        kafkaConsumer.topic = topic;
        kafkaConsumer.kafkaProps = new Properties();
        kafkaConsumer.kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        kafkaConsumer.kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerServer);
        kafkaConsumer.kafkaProps.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, url);
        kafkaConsumer.kafkaProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        kafkaConsumer.kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer.kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");

        kafkaConsumer.kafkaProps .put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        kafkaConsumer.kafkaProps .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

//        kafkaConsumer.hbaseDao = SingletonVariablesShare.INSTANCE.getHbaseDAO();
//        kafkaConsumer.hbaseColumnFamilyName = "Total";
//        kafkaConsumer.hbaseColumnName = "cash";
//        kafkaConsumer.hbaseTableName = "atm:AtmTotalCash";
        return kafkaConsumer;
    }

}