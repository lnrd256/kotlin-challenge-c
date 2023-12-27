package com.example.kotlinchallenge.model

import jakarta.persistence.*

@Entity
@Table(name = "payloads")
data class PayloadModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    var platform: String = "",

    val message: String = "",
)