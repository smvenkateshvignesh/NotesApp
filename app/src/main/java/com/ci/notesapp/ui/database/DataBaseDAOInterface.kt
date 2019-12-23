package com.ci.notesapp.ui.database

import androidx.room.*

@Dao
interface DataBaseDAOInterface {
    @Query("SELECT * FROM DataBaseEntity")
    fun getNotesDetails(): List<DataBaseEntity>

    @Query("SELECT * FROM DataBaseEntity WHERE title = :myTitle")
    fun getFindByTitle(myTitle: String): DataBaseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg dataBaseEntity: DataBaseEntity)

    @Update
    fun updateMyNotes(dataBaseEntity: DataBaseEntity)

    @Delete
    fun delete(dataBaseEntity: DataBaseEntity?)
}