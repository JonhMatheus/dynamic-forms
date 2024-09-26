package com.example.dynamicforms.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FormResponse(
   val title: String,
   val responses: List<ResponseModel>
)