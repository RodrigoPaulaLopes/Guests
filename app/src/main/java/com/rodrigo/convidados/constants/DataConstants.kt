package com.rodrigo.convidados.constants

class DataConstants private constructor(){


    companion object {
        val DATABASE_NAME: String = "guestdb"
    }
     object GUEST {
        val TABLE_NAME: String = "Guest"
        val ID: String = "id"
        val NAME: String = "name"
        val PRESENCE: String = "presence"
    }
}