package com.example.kotlinchallenge.constants

object Constants {
    private const val DEPLOYMENT = "Deployment"
    private const val STATEFUL_SET = "StatefulSet"
    private const val REPLICA_SET = "ReplicaSet"
    const val POD = "Pod"

    val KIND_WITH_CONTAINERS = arrayOf(DEPLOYMENT, STATEFUL_SET, REPLICA_SET)
}