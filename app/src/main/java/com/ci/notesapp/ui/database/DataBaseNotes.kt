package com.ci.notesapp.ui.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder


class DataBaseNotes{


}



























//--------------using sqlite---------------------------

// var dbName = "MyPersonalNotes"
//    var dbTable = "Notes"
//    var id = "ID"
//    var title = "Title"
//    var description = "Description"
//
//    var dbVersion = 1
//
//    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + dbTable + " (" + id + " INTEGER PRIMARY KEY,"+ title + " TEXT, " + description + " TEXT);"
//
//    var sqlDB: SQLiteDatabase? = null
//
//    constructor(context: Context){
//        var db = DatabaseHelperNotes(context)
//        sqlDB = db.writableDatabase
//    }
//
//    inner class DatabaseHelperNotes: SQLiteOpenHelper{
//        var context: Context?= null
//
//        constructor(context: Context): super(context, dbName, null, dbVersion) {
//            this.context = context
//        }
//
//        override fun onCreate(db: SQLiteDatabase?) {
//            db!!.execSQL(sqlCreateTable)
//        }
//
//        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//            db!!.execSQL("Drop table if exists" + dbTable)
//        }
//    }
//
//    fun insert(values: ContentValues): Long {
//        val ID = sqlDB!!.insert(dbTable, "", values)
//        return ID
//    }
//
//    fun Query(projection: Array<String>, selection: String, selectionArgs: Array<String>, sorOrder: String): Cursor {
//        val qb = SQLiteQueryBuilder();
//        qb.tables = dbTable
//        val cursor = qb.query(sqlDB, projection, selection, selectionArgs, null, null, sorOrder)
//        return cursor
//    }
//
//    fun delete(selection: String, selectionArgs: Array<String>): Int {
//        val count = sqlDB!!.delete(dbTable, selection, selectionArgs)
//        return count
//    }
//
//    fun update(values: ContentValues, selection: String, selectionArgs: Array<String>): Int {
//        val count = sqlDB!!.update(dbTable, values, selection, selectionArgs)
//        return count
//    }