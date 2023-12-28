package com.example.kotlinchallenge.service

import com.example.kotlinchallenge.constants.Constants.MAX_TRIES
import com.example.kotlinchallenge.entities.k8s.Manifest
import com.example.kotlinchallenge.interfaces.MessageProcessor
import com.example.kotlinchallenge.model.PayloadModel
import com.example.kotlinchallenge.repositories.PayloadRepository
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PayloadService @Autowired constructor(
    @Qualifier("k8sMessageProcessor") private val k8sMessageProcessor: MessageProcessor,
    private val payloadRepository: PayloadRepository,
    private val k8sService: K8sService,
    private val kafkaProducerService: KafkaProducerService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun processMessage(message: JsonNode): PayloadModel {
        val payload = PayloadModel(message = message.toString())
        var platform = "unknown"

        val processedMessage = k8sMessageProcessor.processMessage(message)
        if (processedMessage != null) {
            platform = "k8s"
        }
        /*
        val processedMessage = otherMessageProcessor.processMessage(message)
        if (processedMessage != null) {
            platform = "otherPlatform"
        }
         */

        payload.platform = platform
        val randomValue = Random.nextInt(1, 11)
        var payloadSaved = PayloadModel()

        if(randomValue != 10) { // 10% of message fail trying to save in database
            payloadSaved = payloadRepository.save(payload)
        }

        if(payloadSaved.id != null) {
            saveMessageProcessed(payloadSaved, processedMessage)
        }else {
            resentPayload(message)
        }

        return payloadSaved
    }

    fun saveMessageProcessed(payloadSaved: PayloadModel, processedMessage: Any?): Any? {
        return when (payloadSaved.platform) {
            "k8s" -> payloadSaved.id?.let { k8sService.saveRecord(it, processedMessage as Manifest) }
            else -> logger.info("unknown platform, only payload was saved")
        }
    }

    fun resentPayload(message: JsonNode) {
        if (message is ObjectNode) {
            val retryMessage = message.get("retry")
            var retry = 0

            if (retryMessage != null) {
                retry = retryMessage.asInt()
            }

            if(retry < MAX_TRIES) {
                message.put("retry", (retry+1).toString())
                kafkaProducerService.sendMessage(message.toString())
                logger.info("cannot save the message applying retry method")
            }else {
                logger.info("cannot save the message too many retries, dropping message $message")
            }
        }
    }
}
