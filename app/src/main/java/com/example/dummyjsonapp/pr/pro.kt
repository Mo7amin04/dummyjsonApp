package com.example.dummyjsonapp.pr


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class pro(
    @SerialName("limit")
    val limit: Int,
    @SerialName("products")
    val products: List<Product>,
    @SerialName("skip")
    val skip: Int,
    @SerialName("total")
    val total: Int
)