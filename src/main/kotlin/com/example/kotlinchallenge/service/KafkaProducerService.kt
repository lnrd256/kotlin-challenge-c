package com.example.kotlinchallenge.service

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${kafka.topics.product}") private val topic: String
) {

    fun sendMessage(message: String) {
        kafkaTemplate.send(topic, message)
    }
}