package com.example.kotlinchallenge.entities

import com.example.kotlinchallenge.model.StatusModel
import com.fasterxml.jackson.annotation.JsonProperty

data class
    Status(
    @JsonProperty("available_replicas")
    val availableReplicas: Int?,

    @JsonProperty("collision_count")
    val collisionCount: Int?,

    @JsonProperty("current_replicas")
    val currentReplicas:  Int?,

    @JsonProperty("current_revision")
    val currentRevision:  String?,

    @JsonProperty("fully_labeled_replicas")
    val fullyLabeledReplicas:   Int?,

    @JsonProperty("observed_generation")
    val observedGeneration: Int?,

    @JsonProperty("ready_replicas")
    val readyReplicas: Int?,

    @JsonProperty("replicas")
    val replicas: Int?,

    @JsonProperty("updated_replicas")
    val updatedReplicas: Int?

)

fun Status.toStatusModel(recordId: Int): StatusModel {
    return StatusModel(
        recordId = recordId,
        availableReplicas = this.availableReplicas,
        collisionCount = this.collisionCount,
        currentReplicas = this.currentReplicas,
        currentRevision = this.currentRevision,
        fullyLabeledReplicas = this.fullyLabeledReplicas,
        observedGeneration = this.observedGeneration,
        readyReplicas = this.readyReplicas,
        replicas = this.replicas,
        updatedReplicas = this.updatedReplicas
    )
}