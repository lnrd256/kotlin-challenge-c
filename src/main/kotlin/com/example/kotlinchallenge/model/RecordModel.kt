package com.example.kotlinchallenge.model

import jakarta.persistence.*

@Entity
@Table(name = "records")
data class RecordModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "api_version")
    val apiVersion: String = "",

    val kind: String? = null,

    @Column(name = "creation_timestamp")
    val creationTimestamp: String = "",

    @Column(name = "generate_name")
    val generateName: String = "",

    val generation: Int? = null,

    val name: String = "",

    val namespace: String = "",

    @Column(name = "resource_version")
    val resourceVersion: String = "",

    val uid: String = "",

    val message: String = "",
)