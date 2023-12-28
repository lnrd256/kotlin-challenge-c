package com.example.kotlinchallenge.config

import com.example.kotlinchallenge.interfaces.MessageProcessor
import com.example.kotlinchallenge.service.processor.K8sMessageProcessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin


@Configuration
class MessageProcessorConfig {
    @Bean
    @Qualifier("k8sMessageProcessor")
    fun k8sMessageProcessor(objectMapper: ObjectMapper): MessageProcessor {
        return K8sMessageProcessor(objectMapper)
    }

}