package com.rodrigo.convidados.viewModel.present

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PresetViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is present Fragment"
    }
    val text: LiveData<String> = _text
}