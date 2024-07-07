package com.rodrigo.convidados.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guests")
class GuestModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int = 0

    @ColumnInfo
    var name: String = ""

    @ColumnInfo
    var presence: Boolean = false

}