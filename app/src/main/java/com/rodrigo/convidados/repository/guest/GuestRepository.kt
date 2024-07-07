package com.rodrigo.convidados.repository.guest

import android.content.ContentValues
import android.content.Context
import androidx.room.Database
import com.rodrigo.convidados.constants.DataConstants
import com.rodrigo.convidados.model.GuestModel


class GuestRepository(context: Context) {

    private val guestDatabase = GuestDatabase.getDatabase(context).guestDao()


    fun insert(guest: GuestModel): Boolean {
        return guestDatabase.insert(guest) > 0

    }

    fun update(guest: GuestModel): Boolean {

        return guestDatabase.update(guest) > 0
    }

    fun delete(id: Int) {
        var guest = guestDatabase.get(id)
        guestDatabase.delete(guest)

    }


    fun getAll(): List<GuestModel> {
        return guestDatabase.getAll()
    }

    fun getAllPresence(): List<GuestModel> {

        return guestDatabase.getAllPresence()

    }

    fun getAllAbsent(): List<GuestModel> {

        return guestDatabase.getAllAbsent()

    }

    fun get(id: Int): GuestModel {
        return guestDatabase.get(id)
    }
}