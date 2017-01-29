package org.tcb;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


import java.util.Properties;



public class KafkaDataProducer {


    public static final String KAFKA_BROKER_LIST = "kafka0:9090,kafka1:9091,kafka2:9092";
    private Producer<String, String> producer = null;
    public final String topic;


    public KafkaDataProducer(String topic){

        this.topic = topic;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_LIST);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);

    }

    public void sendMessage(String key, String value){
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
        producer.send(record);
    }

    public void close(){
        if(producer != null){
            producer.close();
        }
    }

}