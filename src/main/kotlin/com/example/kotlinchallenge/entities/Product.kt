package com.example.kotlinchallenge.entities

import com.fasterxml.jackson.annotation.JsonProperty


data class Product(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("sku")
    val sku: String?
)