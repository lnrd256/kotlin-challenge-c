package com.example.kotlinchallenge

import com.example.kotlinchallenge.model.PayloadModel
import com.example.kotlinchallenge.repositories.Consumer
import com.example.kotlinchallenge.repositories.PayloadRepository
import com.example.kotlinchallenge.service.K8sService
import com.example.kotlinchallenge.service.PayloadService
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
class KotlinChallengeApplicationTest {

	@Autowired
	private lateinit var payloadService: PayloadService

	val objectMapper = ObjectMapper()

	val payloadRepositoryMock = Mockito.mock(PayloadRepository::class.java)
	@Test
	fun testProcessMessage() {
		val jsonNode: JsonNode = objectMapper.valueToTree(mapOf("message" to "m1", "message2" to "m2"))

		val payloadExpected = PayloadModel(id= 1, platform = "unknown", message =  jsonNode.toString())

		`when`(payloadRepositoryMock.save(any())).thenReturn(payloadExpected)

		val payloadService = payloadService.processMessage(jsonNode)

		Assertions.assertEquals(payloadService.id, payloadExpected.id)
	}

	@Test
	fun testProcessMessageFailSavingInDatabase() {
		val jsonNode: JsonNode = objectMapper.valueToTree(mapOf("message" to "m1", "message2" to "m2"))

		val payloadExpected = PayloadModel(platform = "unknown", message =  jsonNode.toString())
		`when`(payloadRepositoryMock.save(any())).thenReturn(payloadExpected)

		val payloadService = payloadService.processMessage(jsonNode)

		Assertions.assertEquals(payloadService.id, null)
	}
}


