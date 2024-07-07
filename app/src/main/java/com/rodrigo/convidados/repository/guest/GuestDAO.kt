package com.rodrigo.convidados.repository.guest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rodrigo.convidados.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM guests WHERE id = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM guests")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM guests WHERE presence = 1")
    fun getAllPresence(): List<GuestModel>

    @Query("SELECT * FROM guests WHERE presence = 0")
    fun getAllAbsent() : List<GuestModel>
}