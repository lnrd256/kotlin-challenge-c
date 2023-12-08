package com.example.kotlinchallenge.service

import com.example.kotlinchallenge.constants.Constants
import com.example.kotlinchallenge.entities.*
import com.example.kotlinchallenge.repositories.ContainerRepository
import com.example.kotlinchallenge.repositories.RecordRepository
import com.example.kotlinchallenge.repositories.StatusRepository
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecordService {

    @Autowired
    lateinit var recordRepository: RecordRepository

    @Autowired
    lateinit var statusRepository: StatusRepository

    @Autowired
    lateinit var containerRepository: ContainerRepository

    fun saveRecord(record: Records) {
        val recordModel = record.toRecordsModel()
        val recordModelSaved = recordRepository.save(recordModel)
        val objectMapper = ObjectMapper()

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        if (recordModelSaved.id != null) {
            saveStatus(record, recordModelSaved.id)

            if (record.kind in Constants.KIND_WITH_CONTAINERS) {
                saveContainerSpecRecord(record, recordModelSaved.id, objectMapper)
            } else if (record.kind == Constants.POD) {
                saveContainerSpecTemplate(record, recordModelSaved.id, objectMapper)
            }
        }
    }

    private fun saveStatus(record: Records, recordId: Int) {
        if (record.status != null) {
            val statusModel = record.status.toStatusModel(recordId)
            statusRepository.save(statusModel)
        }
    }

    private fun saveContainerSpecRecord(record: Records, recordId: Int, objectMapper: ObjectMapper) {
        val spec: SpecRecord = objectMapper.treeToValue(record.spec, SpecRecord::class.java)

        spec.template?.spec?.containers?.forEach { container ->
            val containerModel = container.toContainerModel(recordId)
            containerRepository.save(containerModel)
        }
    }

    private fun saveContainerSpecTemplate(record: Records, recordId: Int, objectMapper: ObjectMapper) {
        val spec: SpecTemplate = objectMapper.treeToValue(record.spec, SpecTemplate::class.java)

        spec.containers.forEach { container ->
            val containerModel = container.toContainerModel(recordId)
            containerRepository.save(containerModel)
        }
    }
}