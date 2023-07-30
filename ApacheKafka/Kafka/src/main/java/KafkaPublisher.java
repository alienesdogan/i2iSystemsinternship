import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
public class KafkaPublisher
{
    public static void main( String[] args ) throws InterruptedException {
        String topicName = "subscriber";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            for (int i = 0; true; i++) {
                int subsc_id = i;
                Value value = new Value(i);
                ProducerRecord<String, String> record = new ProducerRecord<>(topicName, Integer.toString(subsc_id), value.toString());
                producer.send(record, (metadata, exception) -> {
                    if (metadata != null) {
                        System.out.println("Message sent: " + value +
                                ", Partition: " + metadata.partition() +
                                ", Offset: " + metadata.offset());
                    } else {
                        System.err.println("Error while sending message: " + exception.getMessage());
                    }
                });
                Thread.sleep(1000);
            }
        }
    }
}

class Value{
    private String subsc_name;
    private String subsc_surname;
    private String msisdn;

    Value(int i){
        subsc_name = "Name" + i;
        subsc_surname = "Surname" + i;
        msisdn = "This is message for the record " + i;
    }

    @Override
    public String toString() {
        return subsc_name + "," + subsc_surname + "," +
                msisdn;
    }
}