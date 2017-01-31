package org.tcb;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.Predicate;
import org.tcb.utils.SpecificAvroSerde;

import java.util.Properties;


public class DataOrganizer {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "data-organizer-monitor1");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9090,kafka1:9091,kafka2:9092");
        props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "zookeeper:2181");
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://schema-registry:8081");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put("consumer.interceptor.classes", "io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor");
        props.put("producer.interceptor.classes", "io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor");

        final Serde<String> stringSerde = Serdes.String();

        KStreamBuilder builder = new KStreamBuilder();

        KStream<String, String> dataRaw = builder.stream(stringSerde, stringSerde, "data.raw");

        Predicate<String, String> isTypeA = (k, v) -> k.equals("type_a");
        Predicate<String, String> isTypeB = (k, v) -> k.equals("type_b");
        Predicate<String, String> isTypeC = (k, v) -> k.equals("type_c");

        KStream<String, String>[] filteredStreams = dataRaw.branch(isTypeA, isTypeB, isTypeC);

        filteredStreams[0]
                .map(DataParser::parseDataTypeA)
                .filter((k, v) -> k != null && v != null)
                .to("parsed.a");

        filteredStreams[1]
                .map(DataParser::parseDataTypeB)
                .filter((k, v) -> k != null && v != null)
                .to("parsed.b");


        filteredStreams[2]
                .map(DataParser::parseDataTypeC)
                .filter((k, v) -> k != null && v != null)
                .to("parsed.c");


        final KafkaStreams streams = new KafkaStreams(builder, props);
        System.out.println("Starting data streams");
        streams.start();
        System.out.println("Started");

        // Add shutdown hook to respond to SIGTERM and gracefully close Kafka Streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));


    }
}