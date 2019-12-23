package com.ci.notesapp.ui.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ci.notesapp.application.MyApplication

@Database(entities = [(DataBaseEntity::class)], version = 1)
abstract class DataBaseNotes : RoomDatabase() {
    abstract fun dataBaseDaoInterface(): DataBaseDAOInterface

    companion object {
        val dataBase = Room.databaseBuilder(
            MyApplication.getApplicationContext(),
            DataBaseNotes::class.java,
            "MyNotesDB"
        ).build()
    }
}