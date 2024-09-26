package com.example.dynamicforms.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicforms.data.model.FormModel
import com.example.dynamicforms.data.model.FormResponse
import com.example.dynamicforms.data.repositories.FormRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FormViewModel(private val repository: FormRepository) : ViewModel() {

    private val _forms = MutableStateFlow<Map<String, FormModel>>(emptyMap())
    val forms: StateFlow<Map<String, FormModel>> get() = _forms

    var formModel by mutableStateOf<FormModel?>(null)

    fun postFormResponse(formResponse: FormResponse) {
        viewModelScope.launch {
            try {
                val response = repository.postFormResponse(formResponse)
            } catch (e: Exception) {
                println(e);
            }
        }
    }

    fun getForms() {
        viewModelScope.launch {
            try {
                _forms.value = repository.getForms()
            } catch (e: Exception) {
                println(e);
            }
        }
    }
}

