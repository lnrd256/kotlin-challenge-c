package com.example.kotlinchallenge.entities.k8s

import com.example.kotlinchallenge.model.k8s.ManifestModel
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class
    Manifest(
    @JsonProperty("apiVersion")
    val apiVersion: String,

    @JsonProperty("kind")
    val kind: String,

    @JsonProperty("metadata")
    val metadata: Metadata,

    @JsonProperty("string")
    var message: String?,

    @JsonProperty("spec")
    var spec: JsonNode?,

    )

fun Manifest.toManifestModel(payloadId: Int): ManifestModel {
    return ManifestModel(
        apiVersion = this.apiVersion,
        payloadId = payloadId,
        kind = this.kind,
        creationTimestamp = this.metadata.creationTimestamp?:"",
        generateName = this.metadata.generateName?:"",
        generation = this.metadata.generation,
        name = this.metadata.name?:"",
        namespace = this.metadata.namespace?:"",
        resourceVersion = this.metadata.resourceVersion?:"",
        uid = this.metadata.uid?:"",
    )
}