package com.example.kotlinchallenge.repositories

import com.example.kotlinchallenge.model.StatusModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StatusRepository: CrudRepository<StatusModel, Int> {
}

