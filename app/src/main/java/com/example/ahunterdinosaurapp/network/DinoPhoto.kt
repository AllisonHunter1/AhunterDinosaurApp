package com.example.ahunterdinosaurapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DinoPhoto(
    val name: String,
    val length: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)