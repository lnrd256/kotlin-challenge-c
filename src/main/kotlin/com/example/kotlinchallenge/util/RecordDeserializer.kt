package com.example.kotlinchallenge.util

import com.example.kotlinchallenge.entities.Records
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.slf4j.LoggerFactory
import kotlin.text.Charsets.UTF_8


class RecordDeserializer : Deserializer<Records> {
    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): Records? {
        log.info("Deserializing...")
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val stringMessage = String(
            data ?: throw SerializationException("Error when deserializing byte[] to Product"), UTF_8
        )
        val records = objectMapper.readValue(
            stringMessage, Records::class.java
        )

        records.message = stringMessage
        return records
    }

    override fun close() {}
}