package es.ua.eps.sqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast

var selectedUser = ""
var backupCreated = false
var welcomeName = ""
var userName = ""

class SQLiteCode(context: Context): SQLiteOpenHelper(
    context, "usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE usuarios" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_usuario TEXT," +
                "password TEXT," +
                "nombre_completo TEXT," +
                "email TEXT)"
        db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val deleteTable = "DROP TABLE IF EXISTS usuarios"
        db!!.execSQL(deleteTable)
        onCreate(db)
    }

    fun backTable() {
        val db = this.writableDatabase
        val deleteTable = "DROP TABLE IF EXISTS backup"
        val createBack = "CREATE TABLE backup AS SELECT * FROM usuarios"
        db!!.execSQL(deleteTable)
        db.execSQL(createBack)
        backupCreated = true
    }

    fun deleteTable(context: Context) {
        if (backupCreated) {
            val db = this.writableDatabase
            val deleteOrder = "DROP TABLE IF EXISTS usuarios"
            val backOrder = "CREATE TABLE usuarios AS SELECT * FROM backup"
            db!!.execSQL(deleteOrder)
            db.execSQL(backOrder)
        } else {
            Toast.makeText(context, "You need a backup", Toast.LENGTH_SHORT).show()
        }
    }

    fun addData(nombre_usuario: String, password: String, nombre_completo: String, email: String) {
        val data = ContentValues()
        data.put("nombre_usuario", nombre_usuario)
        data.put("password", password)
        data.put("nombre_completo", nombre_completo)
        data.put("email", email)
        val db = this.writableDatabase
        db.insert("usuarios", null, data)
        db.close()
    }

    fun deleteData(nombre_usuario: String) {
        val db = this.writableDatabase
        db.delete("usuarios", "nombre_usuario = ?", arrayOf(nombre_usuario))
        db.close()
    }

    fun updateData(
        nombre_usuario_old: String, nombre_usuario: String, password: String, nombre_completo: String, email: String
    ) {
        val data = ContentValues()
        data.put("nombre_usuario", nombre_usuario)
        data.put("password", password)
        data.put("nombre_completo", nombre_completo)
        data.put("email", email)
        val db = this.writableDatabase
        db.update("usuarios", data, "nombre_usuario = ?", arrayOf(nombre_usuario_old))
        db.close()
    }

}