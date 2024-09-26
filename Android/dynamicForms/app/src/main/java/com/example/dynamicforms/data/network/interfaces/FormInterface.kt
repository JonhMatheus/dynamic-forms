package com.example.dynamicforms.data.network.interfaces

import com.example.dynamicforms.data.model.FormModel
import com.example.dynamicforms.data.model.FormResponse
import com.example.dynamicforms.data.model.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FormInterface {

        @GET("/forms/.json")
        suspend fun getForms(): Map<String, FormModel>

        @POST("/responses/.json")
        suspend fun postFormResponse(@Body formResponse: FormResponse): Response<Unit>

//        @GET("/responses/.json")
//        suspend fun getResponses(): Map<String, FormModel>
}