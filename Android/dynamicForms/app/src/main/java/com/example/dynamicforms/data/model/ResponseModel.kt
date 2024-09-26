package com.example.dynamicforms.data.model

import kotlinx.serialization.Serializable

@Serializable
 data class ResponseModel(
     val fieldName: String,
     val response: String
 )