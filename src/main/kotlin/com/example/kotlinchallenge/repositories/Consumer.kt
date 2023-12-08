package com.example.kotlinchallenge.repositories


import com.example.kotlinchallenge.entities.Records
import com.example.kotlinchallenge.service.RecordService
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var recordService: RecordService

    @KafkaListener(topics = ["\${kafka.topics.product}"], groupId = "kotlin-challenge")
    fun listenGroupFoo(consumerRecord: ConsumerRecord<Any, Any>) {
        val records = consumerRecord.value() as Records
        logger.info("Message received {}", records)
        recordService.saveRecord(consumerRecord.value() as Records)

    }
}