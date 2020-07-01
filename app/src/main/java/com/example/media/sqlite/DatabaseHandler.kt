package com.example.media.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/*
* SQLiteOpenHelper: A helper class to manage database creation and version management.
* Cursor: implementation that exposes results from a query on a SQLiteDatabase.
*         SQLiteCursor is not internally synchronized so code using a SQLiteCursor from multiple
*         threads should perform its own synchronization when using the SQLiteCursor.
 */
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "EmployeeDatabase"
        private val TABLE_CONTACTS = "EmployeeTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.i("SQLite", "DatabaseHandler: onCreate")
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    // For newer database version
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("SQLite", "DatabaseHandler: onUpgrade")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    // For older database version
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("SQLite", "DatabaseHandler: onDowngrade")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    //method to insert data
    fun insertEmployee(emp: EmpModelClass):Long{
        Log.i("SQLite", "DatabaseHandler: insertEmployee "+emp.userId+", "+emp.userName+", "+emp.userEmail)
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId)
        contentValues.put(KEY_NAME, emp.userName) // EmpModelClass Name
        contentValues.put(KEY_EMAIL,emp.userEmail) // EmpModelClass Phone
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to read data
    fun viewEmployee():List<EmpModelClass>{
        Log.i("SQLite", "DatabaseHandler: viewEmployee")
        val empList:ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var userName: String
        var userEmail: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                userName = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                userEmail = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))
                val emp= EmpModelClass(userId, userName, userEmail)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

    //method to update data
    fun updateEmployee(emp: EmpModelClass):Int{
        Log.i("SQLite", "DatabaseHandler: updateEmployee "+emp.userId+", "+emp.userName+", "+emp.userEmail)
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId)
        contentValues.put(KEY_NAME, emp.userName) // EmpModelClass Name
        contentValues.put(KEY_EMAIL,emp.userEmail ) // EmpModelClass Email

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues,"id="+emp.userId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to delete data
    fun deleteEmployee(emp: EmpModelClass):Int{
        Log.i("SQLite", "DatabaseHandler: deleteEmployee "+ emp.userId)
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.userId) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS,"id="+emp.userId,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}