package com.rodrigo.convidados.viewModel.guestForm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rodrigo.convidados.model.GuestModel
import com.rodrigo.convidados.repository.guest.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {


    val repository: GuestRepository = GuestRepository.getInstance(application)

    fun save(guest: GuestModel) : Boolean {
        return repository.save(guest)
    }

    fun update(guest: GuestModel) : Boolean {
        return repository.update(guest)
    }




}