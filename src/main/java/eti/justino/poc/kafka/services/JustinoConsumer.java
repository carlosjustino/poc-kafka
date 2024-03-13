package eti.justino.poc.kafka.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class JustinoConsumer {

    @KafkaListener(id = "client1", topicPartitions =
            { @TopicPartition(topic = "${justino.kafka.topico}", partitions = { "0", "1" })
                    /*,
                    @TopicPartition(topic = "topic2", partitions = "0",
                            partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))*/
                })
    public void onMessage(ConsumerRecord<?, ?> record) {
        System.out.printf("Mensagem recebida: %s,%s,%s,%s ",record.timestamp(),record.timestampType(),record.key(),record.value() );
    }
}
