package com.example.kotlinchallenge.repositories

import com.example.kotlinchallenge.model.PayloadModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PayloadRepository: CrudRepository<PayloadModel, Int> {
}

