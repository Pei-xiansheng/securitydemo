package com.zap.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
/**
 * @ClassName SecurityListener
 * @Author Evan
 * @Descrption kafka监听器
 * @create 2022/9/5 15:50
 */
@Component
@Slf4j
public class SecurityListener {

    @Value("${kafka.consumer.group.topic}")
    private String kafkaTopic;

    @Value("${kafka.consumer.group.id}")
    private String kafkaGroupId;

    @Value("${kafka.consumer.group.factory}")
    private String kafkaFactory;

    @KafkaListener(id = "securityListener", groupId = "${kafka.consumer.group.id}", topics = "kafka.consumer.group.topic", containerFactory = "${kafka.consumer.group.factory}")
    public void listener(ConsumerRecord<String, String> record) {
        try {
            dealMsg(record);
        } catch (Exception e) {
           log.info(e.getMessage());
        }
    }

    /**
     * 处理消息数据
     *
     * @param record
     */
    private void dealMsg(ConsumerRecord<String, String> record) {
        String key = record.key();
        log.info("接收到kafka消息 ,key:{} , value:{}", key, record.value());
    }

}
