package es.ua.eps.sqlite

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class ProviderCode: ContentProvider() {

    companion object {
        val PROVIDER_NAME = "es.ua.eps.sqlite/ProviderCode"
        val URL = "content://$PROVIDER_NAME/usuarios"
        val CONTENT_URI = Uri.parse(URL)
        val _ID = "_id"
        val NOMBRE_USUARIO = "nombre_usuario"
        val PASSWORD = "password"
        val NOMBRE_COMPLETO = "nombre_completo"
        val EMAIL = "email"
    }

    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        val sqlUsers = SQLiteCode(getContext()!!)
        db = sqlUsers.writableDatabase
        return false
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert("usuarios",null,cv)
        context?.contentResolver?.notifyChange(uri,null)
        return uri
    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.delete("usuarios", condition, condition_val)
        context?.contentResolver?.notifyChange(uri,null)
        return count
    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
        val count = db.update("usuarios", cv, condition, condition_val)
        context?.contentResolver?.notifyChange(uri,null)
        return count
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query("usuarios",cols,condition,condition_val,null,null,order)
    }

    override fun getType(p0: Uri): String? {
        return "vnd.android.cursor.dir/vnd.example.usuarios"
    }

}