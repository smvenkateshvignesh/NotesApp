package com.ci.notesapp.ui.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class DataBaseEntity(
    var title: String? = null,
    var description: String? = null

):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @Ignore
    var isShowingEdit = false
}
