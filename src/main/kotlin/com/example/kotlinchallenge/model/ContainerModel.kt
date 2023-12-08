package com.example.kotlinchallenge.model

import jakarta.persistence.*

@Entity
@Table(name = "containers")
data class ContainerModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "record_id")
    val recordId: Int = -1,

    @Column(name = "image")
    val image: String? = null,

    @Column(name = "image_pull_policy")
    val imagePullPolicy: String? = null,

    @Column(name = "cpu_limit")
    val cpuLimit: String? = null,

    @Column(name = "memory_limit")
    val memoryLimit: String? = null,

    @Column(name = "cpu_request")
    val cpuRequest: String? = null,

    @Column(name = "memory_request")
    val memoryRequest: String? = null,

)