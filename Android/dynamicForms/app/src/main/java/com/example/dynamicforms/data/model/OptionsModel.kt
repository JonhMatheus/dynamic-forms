package com.example.dynamicforms.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OptionsModel(
    val label: String,
    val value: String
)