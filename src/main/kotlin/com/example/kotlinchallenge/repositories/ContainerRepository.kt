package com.example.kotlinchallenge.repositories

import com.example.kotlinchallenge.model.ContainerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContainerRepository: CrudRepository<ContainerModel, Int> {
}

