package com.example.kotlinchallenge.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory
import kotlin.text.Charsets.UTF_8


class PayloadDeserializer : Deserializer<JsonNode> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): JsonNode? {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        var response: JsonNode? = null

        val stringMessage = String(
            data ?: throw SerializationException("Error when deserializing byte[] to Product"), UTF_8
        )
        try {
            response = objectMapper.readValue(
                stringMessage, JsonNode::class.java
            )
        } catch (e: Exception) {
            log.info("Message is not a Json message")
        }

        return response
    }

    override fun close() {}
}