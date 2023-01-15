package es.ua.eps.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.ua.eps.sqlite.databinding.ActivityNewUserBinding

class NewUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewUserBinding.inflate(layoutInflater)
        val sqlUsers = SQLiteCode(this)
        setContentView(binding.root)
        with(binding) {
            btnNewBack.setOnClickListener {
                this@NewUser.finish()
            }
            btnNewUser.setOnClickListener {
                if (etNewLogin.text.isNotBlank() &&
                    etNewPass.text.isNotBlank() &&
                    etNewName.text.isNotBlank()) {

                    sqlUsers.addData(
                            "${etNewLogin.text}",
                            "${etNewPass.text}",
                            "${etNewName.text}",
                            "${etNewLogin.text}@mail.ua"
                    )

                    etNewLogin.text.clear()
                    etNewPass.text.clear()
                    etNewName.text.clear()
                }
                else {
                    Toast.makeText(this@NewUser, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}