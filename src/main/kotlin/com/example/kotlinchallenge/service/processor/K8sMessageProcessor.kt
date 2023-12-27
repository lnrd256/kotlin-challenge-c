package com.example.kotlinchallenge.service.processor

import com.example.kotlinchallenge.entities.k8s.Manifest
import com.example.kotlinchallenge.interfaces.MessageProcessor
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class K8sMessageProcessor @Autowired constructor(private val objectMapper: ObjectMapper) : MessageProcessor {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun processMessage(message: JsonNode): Manifest? {
        try {
            return objectMapper.treeToValue(message, Manifest::class.java)
        } catch (e: Exception) {
            logger.info("message is not a K8s Manifest")
        }
        return null
    }
}

