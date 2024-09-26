package com.example.dynamicforms.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FormModel(
     val title: String,
     val fields: List<FieldModel>
)
