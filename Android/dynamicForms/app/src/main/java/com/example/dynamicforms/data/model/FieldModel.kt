package com.example.dynamicforms.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FieldModel(
    val type: String,
    val label: String,
    val name: String,
    val required: Boolean,
    val uuid: String,
    val options: List<OptionsModel>?
)
