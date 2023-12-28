package com.example.kotlinchallenge

import com.example.kotlinchallenge.model.PayloadModel
import com.example.kotlinchallenge.repositories.PayloadRepository
import com.example.kotlinchallenge.service.K8sService
import com.example.kotlinchallenge.service.KafkaProducerService
import com.example.kotlinchallenge.service.PayloadService
import com.example.kotlinchallenge.service.processor.K8sMessageProcessor
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = [ "listeners=PLAINTEXT://localhost:9092", "port=9092" ])
class KotlinChallengeApplicationTest {

	@InjectMocks
	private lateinit var payloadService: PayloadService

	val objectMapper = ObjectMapper()

	@Mock
	lateinit var payloadRepositoryMock: PayloadRepository

	@Mock
	lateinit var k8sMessageProcessor: K8sMessageProcessor

	@Mock
	lateinit var k8sService: K8sService

	@Mock
	lateinit var kafkaProducerService: KafkaProducerService

	@Test
	fun testProcessMessage() {
		val jsonNode: JsonNode = objectMapper.valueToTree(mapOf("message" to "m1", "message2" to "m2"))

		val payloadExpected = PayloadModel(id=1, platform = "unknown", message =  jsonNode.toString())

		`when`(payloadRepositoryMock.save(any())).thenReturn(payloadExpected)

		val payloadService = payloadService.processMessage(jsonNode)

		Assertions.assertNotNull(payloadService.id)
	}

	@Test
	fun testProcessMessageFailSavingInDatabase() {
		val jsonNode: JsonNode = objectMapper.valueToTree(mapOf("message" to "m1", "message2" to "m2"))

		val payloadExpected = PayloadModel(platform = "unknown", message =  jsonNode.toString())
		`when`(payloadRepositoryMock.save(any())).thenReturn(payloadExpected)

		val payloadService = payloadService.processMessage(jsonNode)

		Assertions.assertNull(payloadService.id)
	}
}


