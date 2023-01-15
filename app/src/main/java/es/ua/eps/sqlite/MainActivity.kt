package es.ua.eps.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import es.ua.eps.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnLogin.setOnClickListener{
                if (etUser.text.isNotBlank() &&
                    etPass.text.isNotBlank()) {
                    val cursor = contentResolver.query(
                        ProviderCode.CONTENT_URI,
                        arrayOf(ProviderCode.NOMBRE_COMPLETO, ProviderCode.NOMBRE_USUARIO),
                        "${ProviderCode.NOMBRE_USUARIO} = ? AND ${ProviderCode.PASSWORD} = ?",
                        arrayOf(etUser.text.toString(),etPass.text.toString()),
                        null)
                    if (cursor!!.moveToFirst()) {
                        welcomeName = cursor.getString(0).toString()
                        userName = cursor.getString(1).toString()
                        cursor.close()
                        Intent(this@MainActivity,UserData::class.java).also {
                            startActivity(it)
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Error usuario/password incorrectos", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Toast.makeText(this@MainActivity, "Error usuario/password incorrectos", Toast.LENGTH_LONG).show()
                }
            }
            btnClose.setOnClickListener{
                this@MainActivity.finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sqlUsers = SQLiteCode(this)
        when(item.itemId) {
            R.id.itCreate -> sqlUsers.backTable()
            R.id.itRestore -> sqlUsers.deleteTable(this)
            R.id.itManage -> Intent(this@MainActivity,UserManagement::class.java).also {
                startActivity(it)
            }
        }
        return true
    }
}