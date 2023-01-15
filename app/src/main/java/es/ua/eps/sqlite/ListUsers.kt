package es.ua.eps.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.sqlite.databinding.ActivityListUsersBinding

class ListUsers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sqlUsers = SQLiteCode(this)
        val db = sqlUsers.readableDatabase
        val cursor = db.rawQuery("SELECT nombre_usuario, email FROM usuarios", null)
        with(binding) {
            tvList.text = "Name & Email: \n"

            if (cursor.moveToFirst()) {
                do {
                    tvList.append(cursor.getString(0).toString()+": ")
                    tvList.append(cursor.getString(1).toString()+"\n")
                } while (cursor.moveToNext())
            }

            cursor.close()

            btnList.setOnClickListener {
                this@ListUsers.finish()
            }

        }
    }
}