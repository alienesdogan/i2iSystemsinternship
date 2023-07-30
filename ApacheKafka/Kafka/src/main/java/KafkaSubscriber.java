import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaSubscriber {
    public static void main(String[] args) {
        String topicName = "subscriber";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "subscriber-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        try (Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList(topicName));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(10));
                for (ConsumerRecord<String, String> record : records) {
                    String key = record.key();
                    String[] dataFields = record.value().split(",");

                    String name = dataFields[0];
                    String surname = dataFields[1];
                    String message = dataFields[2];

                    System.out.println("Received message: " +
                            "Key = " + key +
                            ", ID = " + record.key() +
                            ", Name = " + name +
                            ", Surname = " + surname +
                            ", Message = " + message +
                            ", Partition = " + record.partition() +
                            ", Offset = " + record.offset());
                }
            }
        }
    }
}