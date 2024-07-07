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


    fun save(guest: GuestModel): Boolean {
        return try {
            var db = guestDatabase.writableDatabase
            var values = ContentValues()


            var presence = if (guest.presence) 1 else 0

            values.put(DataConstants.GUEST.NAME, guest.name)
            values.put(DataConstants.GUEST.PRESENCE, presence)

            db.insert("Guest", null, values)
            true
        } catch (e: Exception) {
            println(e.message)
            false
        }

    }

    fun update(guest: GuestModel): Boolean {

        return try {
            val db = guestDatabase.writableDatabase
            var presence = if (guest.presence) 1 else 0
            val values = ContentValues()
            values.put(DataConstants.GUEST.NAME, guest.name)
            values.put(DataConstants.GUEST.PRESENCE, presence)

            db.update(
                DataConstants.GUEST.TABLE_NAME,
                values,
                "id = ?",
                arrayOf(guest.id.toString())
            )
            true
        } catch (e: Exception) {
            print(e.message)
            false
        }
    }

    fun delete(id: Int): Boolean {

        return try {
            val db = guestDatabase.writableDatabase

            db.delete(DataConstants.GUEST.TABLE_NAME, "id = ?", arrayOf(id.toString()))
            true
        } catch (e: Exception) {
            print(e.message)
            false
        }
    }


    fun getAll() : List<GuestModel> {

        val data: MutableList<GuestModel> = mutableListOf()

        try {
            val db = guestDatabase.readableDatabase

            val projection = arrayOf(DataConstants.GUEST.ID, DataConstants.GUEST.NAME, DataConstants.GUEST.PRESENCE)

            val cursor = db.query(
                DataConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0){


                while (cursor.moveToNext()) {
                    val id: Int =  cursor.getInt(cursor.getColumnIndex(DataConstants.GUEST.ID))
                    val name: String =  cursor.getString(cursor.getColumnIndex(DataConstants.GUEST.NAME))
                    val presence: Int =  cursor.getInt(cursor.getColumnIndex(DataConstants.GUEST.PRESENCE))

                    var guest = GuestModel(id, name, presence == 1)
                    data.add(guest)
                }
            }

            cursor.close()

        }catch (e: Exception){
            return data
        }

        return data

    }

    fun getAllPresence() : List<GuestModel> {

        val data: MutableList<GuestModel> = mutableListOf()

        try {
            val db = guestDatabase.readableDatabase


            val cursor = db.rawQuery("SELECT * FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0){


                while (cursor.moveToNext()) {
                    val id: Int =  cursor.getInt(cursor.getColumnIndex(DataConstants.GUEST.ID))
                    val name: String =  cursor.getString(cursor.getColumnIndex(DataConstants.GUEST.NAME))
                    val presence: Int =  cursor.getInt(cursor.getColumnIndex(DataConstants.GUEST.PRESENCE))

                    var guest = GuestModel(id, name, presence == 1)
                    data.add(guest)
                }
            }

            cursor.close()

        }catch (e: Exception){
            return data
        }

        return data

    }

    fun getAllAbsent() : List<GuestModel> {

        val data: MutableList<GuestModel> = mutableListOf()

        try {
            val db = guestDatabase.readableDatabase


            val cursor = db.rawQuery("SELECT * FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0){


                while (cursor.moveToNext()) {
                    val id: Int =  cursor.getInt(cursor.getColumnIndex(DataConstants.GUEST.ID))
                    val name: String =  cursor.getString(cursor.getColumnIndex(DataConstants.GUEST.NAME))
                    val presence: Int =  cursor.getInt(cursor.getColumnIndex(DataConstants.GUEST.PRESENCE))

                    var guest = GuestModel(id, name, presence == 1)
                    data.add(guest)
                }
            }

            cursor.close()

        }catch (e: Exception){
            return data
        }

        return data

    }

    fun get(id: Int) : GuestModel? {
        var data: GuestModel? = null

        try {
            val db = guestDatabase.readableDatabase

            var args = arrayOf(id.toString())
            val cursor = db.rawQuery("SELECT * FROM Guest WHERE id = ?", args)

            if (cursor != null && cursor.count > 0){

                while (cursor.moveToNext()) {
                    val id: Int =  cursor.getInt(cursor.getColumnIndex(DataConstants.GUEST.ID))
                    val name: String =  cursor.getString(cursor.getColumnIndex(DataConstants.GUEST.NAME))
                    val presence: Int =  cursor.getInt(cursor.getColumnIndex(DataConstants.GUEST.PRESENCE))

                    var guest = GuestModel(id, name, presence == 1)
                    data = guest
                }
            }

            cursor.close()

        }catch (e: Exception){
            return data
        }

        return data
    }
}