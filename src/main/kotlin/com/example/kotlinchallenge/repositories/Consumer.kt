package com.example.kotlinchallenge.repositories


import com.example.kotlinchallenge.service.PayloadService
import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var payloadService: PayloadService

    @KafkaListener(topics = ["\${kafka.topics.product}"], groupId = "kotlin-challenge")
    fun readMessage(consumerRecord: ConsumerRecord<Any, Any>) {
        logger.info("Message received {}", consumerRecord.value())

        if(consumerRecord.value() != null) {
            payloadService.processMessage(consumerRecord.value() as JsonNode)
        }
    }
}