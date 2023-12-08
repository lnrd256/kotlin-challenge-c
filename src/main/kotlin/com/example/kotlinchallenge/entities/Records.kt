package com.example.kotlinchallenge.entities

import com.example.kotlinchallenge.model.RecordModel
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class
    Records(
    @JsonProperty("apiVersion")
    val apiVersion: String,

    @JsonProperty("kind")
    val kind: String,

    @JsonProperty("metadata")
    val metadata: Metadata,

    @JsonProperty("status")
    val status: Status?,

    @JsonProperty("string")
    var message: String?,

    @JsonProperty("spec")
    var spec: JsonNode?,

    )

fun Records.toRecordsModel(): RecordModel {
    return RecordModel(
        apiVersion = this.apiVersion,
        kind = this.kind,
        creationTimestamp = this.metadata.creationTimestamp?:"",
        generateName = this.metadata.generateName?:"",
        generation = this.metadata.generation,
        name = this.metadata.name?:"",
        namespace = this.metadata.namespace?:"",
        resourceVersion = this.metadata.resourceVersion?:"",
        uid = this.metadata.uid?:"",
        message = this.message?:""
    )
}