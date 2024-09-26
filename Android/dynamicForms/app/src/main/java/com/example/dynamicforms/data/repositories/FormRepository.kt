package com.example.dynamicforms.data.repositories

import com.example.dynamicforms.data.network.interfaces.FormInterface
import com.example.dynamicforms.data.model.FormModel
import com.example.dynamicforms.data.model.FormResponse
import com.example.dynamicforms.data.model.ResponseModel
import retrofit2.Response

class FormRepository(private val apiService: FormInterface){

    suspend fun getForms(): Map<String, FormModel> {
        return apiService.getForms()
    }

    suspend fun postFormResponse(formResponse: FormResponse): Response<Unit> {
        return apiService.postFormResponse(formResponse)
    }
}