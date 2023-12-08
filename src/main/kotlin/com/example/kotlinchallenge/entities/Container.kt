package com.example.kotlinchallenge.entities

import com.example.kotlinchallenge.model.ContainerModel
import com.fasterxml.jackson.annotation.JsonProperty

data class Container(
    @JsonProperty("image")
    val image: String?,

    @JsonProperty("image_pull_policy")
    val imagePullPolicy: String?,

    @JsonProperty("resources")
    val resources: Resources?,
)

data class Resources(
    @JsonProperty("limits")
    val limits: ResourceSpecification?,

    @JsonProperty("requests")
    val requests: ResourceSpecification?,

    )
data class ResourceSpecification(
    @JsonProperty("cpu")
    val cpu: String?,

    @JsonProperty("memory")
    val memory: String?,

)

fun Container.toContainerModel(recordId: Int): ContainerModel {
    return ContainerModel(
        recordId = recordId,
        image = this.image,
        imagePullPolicy = this.imagePullPolicy,
        cpuLimit = this.resources?.limits?.cpu,
        memoryLimit = this.resources?.limits?.memory,
        cpuRequest = this.resources?.requests?.cpu,
        memoryRequest = this.resources?.requests?.memory
    )
}