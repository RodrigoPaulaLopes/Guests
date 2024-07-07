package com.rodrigo.convidados.viewModel.guestForm

import android.app.Application
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodrigo.convidados.model.GuestModel
import com.rodrigo.convidados.repository.guest.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {


    val repository: GuestRepository = GuestRepository.getInstance(application)


    private val guestModel: MutableLiveData<GuestModel> = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _isSaved: MutableLiveData<String> = MutableLiveData<String>()
    val isSaved: LiveData<String> = _isSaved

    fun save(guest: GuestModel): Unit {
        if (guest.id == 0) {
            if (repository.save(guest)) {
                _isSaved.value = "Inserção com sucesso"
            } else {
                _isSaved.value = "Falha"
            }
        } else {
            if (repository.update(guest)) {
                _isSaved.value = "Alteração com sucesso"
            } else {
                _isSaved.value = "Falha"
            }


        }
    }

    fun get(id: Int): Boolean {
        guestModel.value = repository.get(id)
        return true
    }


}