package com.example.kotlinchallenge.model

import jakarta.persistence.*

@Entity
@Table(name = "status")
data class StatusModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "record_id")
    val recordId: Int = -1,

    @Column(name = "available_replicas")
    val availableReplicas: Int? = null,

    @Column(name = "collision_count")
    val collisionCount: Int? = null,

    @Column(name = "current_replicas")
    val currentReplicas: Int? = null,

    @Column(name = "current_revision")
    val currentRevision: String? = null,

    @Column(name = "fully_labeled_replicas")
    val fullyLabeledReplicas: Int? = null,

    @Column(name = "observed_generation")
    val observedGeneration: Int? = null,

    @Column(name = "replicas")
    val replicas: Int? = null,

    @Column(name = "ready_replicas")
    val readyReplicas: Int? = null,

    @Column(name = "updated_replicas")
    val updatedReplicas: Int? = null,
)