package com.example.kotlinchallenge.service

import com.example.kotlinchallenge.entities.k8s.*
import com.example.kotlinchallenge.model.k8s.ManifestModel
import com.example.kotlinchallenge.repositories.ManifestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class K8sService {

    @Autowired
    lateinit var k8sManifestRepository: ManifestRepository

    fun saveRecord(payloadId: Int, manifest: Manifest): ManifestModel {
        val manifestModel = manifest.toManifestModel(payloadId)

        return k8sManifestRepository.save(manifestModel)
    }


}