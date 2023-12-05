package com.example.kotlinchallenge.controllers

import com.example.kotlinchallenge.repository.RecordRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController {
    @Autowired
    lateinit var repository: RecordRepository

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun get(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(repository.findAll())
    }
}