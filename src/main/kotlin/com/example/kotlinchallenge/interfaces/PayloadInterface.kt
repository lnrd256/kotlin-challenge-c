package com.example.kotlinchallenge.interfaces

import com.fasterxml.jackson.databind.JsonNode

interface MessageProcessor {
    fun processMessage(message: JsonNode): Any?
}