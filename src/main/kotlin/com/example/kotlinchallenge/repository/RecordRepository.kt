package com.example.kotlinchallenge.repository

import com.example.kotlinchallenge.model.RecordMessage
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RecordRepository: CrudRepository<RecordMessage, Long> {
}