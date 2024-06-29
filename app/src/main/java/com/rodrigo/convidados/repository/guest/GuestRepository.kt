package com.rodrigo.convidados.repository.guest

import android.content.ContentValues
import android.content.Context
import com.rodrigo.convidados.constants.DataConstants
import com.rodrigo.convidados.model.GuestModel

class GuestRepository private constructor(context: Context) {

    private val guestDatabase: GuestDatabase = GuestDatabase(context)

    companion object {
        private lateinit var instance: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!Companion::instance.isInitialized) {
                instance = GuestRepository(context)
            }
            return instance
        }
    }


    fun save(guest: GuestModel) : Boolean {
        return try {
            var db = guestDatabase.writableDatabase
            var values = ContentValues()


            var presence = if (guest.presence) 1 else 0

            values.put(DataConstants.NAME, guest.name)
            values.put(DataConstants.PRESENCE ,presence)

            db.insert("Guest", null, values)
            true
        } catch (e: Exception ) {
            println(e.message)
            false
        }

    }

}