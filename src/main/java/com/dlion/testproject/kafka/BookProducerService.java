package com.dlion.testproject.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author lzy
 * @date 2020/10/16
 */
@Service
public class BookProducerService {
    private static final Logger logger = LoggerFactory.getLogger(BookProducerService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BookProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(String topic, Object o) {
        //如果我们想在发送的时候带上timestamp（时间戳）、key等信息的话，sendMessage()方法可以这样写：
        // 分区编号最好为 null，交给 kafka 自己去分配
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, null, System.currentTimeMillis(), String.valueOf(o.hashCode()), o);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(producerRecord);
        future.addCallback(result -> logger.info("生产者成功发送消息到topic:{} partition:{}的消息", result.getRecordMetadata().topic(), result.getRecordMetadata().partition()),
                ex -> logger.error("生产者发送消失败，原因：{}", ex.getMessage()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void sendTxMessage(String topic, Object o) {
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback<String, Object, Object>() {
            @Override
            public Object doInOperations(KafkaOperations<String, Object> operations) {
                // 发送消息
                operations.send(topic, o);
                // 模拟发生异常
                int a = 1 / 0;
                // 模拟业务操作
                //sendedBooks.add(o);
                return null;
            }
        });
    }

    /**
     * Spring-Kafka 将这种正常情况下无法被消费的消息称为死信消息（Dead-Letter Message），
     * 将存储死信消息的特殊队列称为死信队列（Dead-Letter Queue）。
     * @param topic
     * @param o
     */
    @Transactional(rollbackFor = Exception.class)
    public void sendTxMessage2(String topic, Object o) {
        kafkaTemplate.executeInTransaction(kafkaOperations -> {
            // 发送消息
            kafkaOperations.send(topic, o);
            // 模拟发生异常
            int a = 1 / 0;
            // 模拟业务操作
            //sendedBooks.add(o);
            return null;
        });
    }
}
