package es.ua.eps.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import es.ua.eps.sqlite.databinding.ActivityUserManagementBinding

class UserManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserManagementBinding.inflate(layoutInflater)
        val sqlUsers = SQLiteCode(this)
        val db = sqlUsers.readableDatabase
        val cursor = db.rawQuery("SELECT nombre_usuario FROM usuarios", null)
        setContentView(binding.root)
        var spinner_data = arrayOf<String>()
        if (cursor.moveToFirst()) {
            do {
                spinner_data += cursor.getString(0).toString()
            } while (cursor.moveToNext())
        }
        cursor.close()
        val adapter = ArrayAdapter<String>(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, spinner_data
        )

        with(binding) {
            spUsuario.adapter = adapter
            spUsuario.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedUser = p0?.getItemAtPosition(p2).toString()
                }
            }
            btnNuevo.setOnClickListener {
                Intent(this@UserManagement,NewUser::class.java).also {
                    startActivity(it)
                }
            }
            btnActualizar.setOnClickListener {
                Intent(this@UserManagement,UpdateUser::class.java).also {
                    startActivity(it)
                }
            }
            btnEliminar.setOnClickListener {
                sqlUsers.deleteData(selectedUser)
                this@UserManagement.recreate()
            }
            btnLista.setOnClickListener {
                Intent(this@UserManagement,ListUsers::class.java).also {
                    startActivity(it)
                }
            }
            btnVolver.setOnClickListener {
                this@UserManagement.finish()
            }
        }
    }

    override fun onRestart() {
        this.recreate()
        super.onRestart()
    }
}