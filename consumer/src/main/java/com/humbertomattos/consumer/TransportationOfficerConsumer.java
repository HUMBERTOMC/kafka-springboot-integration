package com.humbertomattos.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.humbertomattos.consumer.model.Order;

@Component
@Slf4j
public class TransportationOfficerConsumer {

	@KafkaListener(topics = "${order.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumer(final ConsumerRecord<String, Order> consumerRecord) {
		log.info("============= CONSUMER ======================");
		log.info("key: " + consumerRecord.key());
		log.info("Headers: " + consumerRecord.headers());
		log.info("Partion: " + consumerRecord.partition());
		log.info("Receive the Order: \n" + consumerRecord.value());
		log.info("Collect the package: OK \n");
		log.info("Shipping the package: OK \n");
		log.info("=============================================");

	}
}