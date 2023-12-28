package com.example.kotlinchallenge.repositories

import com.example.kotlinchallenge.model.k8s.ManifestModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ManifestRepository: CrudRepository<ManifestModel, Long> {
}

