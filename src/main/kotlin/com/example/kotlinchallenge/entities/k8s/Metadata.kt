package com.example.kotlinchallenge.entities.k8s

import com.fasterxml.jackson.annotation.JsonProperty

data class
    Metadata(

    @JsonProperty("creationTimestamp")
    val creationTimestamp: String?,

    @JsonProperty("generateName")
    val generateName: String?,

    @JsonProperty("generation")
    val generation: Int?,

    @JsonProperty("name")
    val name: String?,

    @JsonProperty("namespace")
    val namespace: String?,

    @JsonProperty("resourceVersion")
    val resourceVersion: String?,

    @JsonProperty("uid")
    val uid: String?


)