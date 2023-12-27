package com.example.kotlinchallenge.service

import com.example.kotlinchallenge.service.processor.K8sMessageProcessor
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class K8sMessageProcessorTest {

    @Autowired
    private lateinit var k8sMessageProcessor: K8sMessageProcessor

    val objectMapper = ObjectMapper()
    @Test
    fun testProcessMessageSuccess() {
        val jsonNode: JsonNode = objectMapper.valueToTree(mapOf("apiVersion" to "V1", "kind" to "deployment", "metadata" to Metadata()))

        val response = k8sMessageProcessor.processMessage(jsonNode)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(response?.kind, "deployment")
    }

    @Test
    fun testProcessMessageFail() {
        val jsonNode: JsonNode = objectMapper.valueToTree(mapOf("apiVersion" to "V1", "kind" to "deployment"))

        val response = k8sMessageProcessor.processMessage(jsonNode)

        Assertions.assertNull(response)
    }

}