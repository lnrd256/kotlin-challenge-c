package com.example.kotlinchallenge.repositories

import com.example.kotlinchallenge.model.RecordModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RecordRepository: CrudRepository<RecordModel, Long> {
}

