package com.example.kotlinchallenge.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class SpecRecord(@JsonProperty("template") val template: Template?)

data class Template(@JsonProperty("spec") val spec: SpecTemplate?)

data class SpecTemplate(
    @JsonProperty("containers")
    val containers: List<Container>,

    @JsonProperty("scheduler_name")
    val schedulerName: String?
)

