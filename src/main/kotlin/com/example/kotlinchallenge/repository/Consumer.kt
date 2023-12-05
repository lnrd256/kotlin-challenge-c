package com.example.kotlinchallenge.repository

import com.example.kotlinchallenge.model.RecordMessage
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var repository: RecordRepository

    @KafkaListener(topics = ["\${kafka.topics.product}"], groupId = "kotlin-challenge")
    fun listenGroupFoo(consumerRecord: ConsumerRecord<Any, Any>) {
        logger.info("Message received {}", consumerRecord)
        repository.save(RecordMessage(message = consumerRecord.value().toString()))
    }
}