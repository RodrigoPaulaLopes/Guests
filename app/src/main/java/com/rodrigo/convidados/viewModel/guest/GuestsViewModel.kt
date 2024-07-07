package com.rodrigo.convidados.viewModel.guest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rodrigo.convidados.model.GuestModel
import com.rodrigo.convidados.repository.guest.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    val repository: GuestRepository = GuestRepository.getInstance(application.applicationContext)

   val listAllGuest : MutableLiveData<List<GuestModel>> = MutableLiveData<List<GuestModel>>()
   val guests : LiveData<List<GuestModel>> = listAllGuest

    fun delete(id: Int) : Boolean {
        return repository.delete(id)

    }


    fun getPresents() {
        listAllGuest.value = repository.getAllPresence()
    }
    fun getAbsents() {

        listAllGuest.value = repository.getAllAbsent()
    }
    fun getAll()  {
        listAllGuest.value = repository.getAll()
    }



}