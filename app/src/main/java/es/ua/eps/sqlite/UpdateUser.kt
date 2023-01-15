package es.ua.eps.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.ua.eps.sqlite.databinding.ActivityUpdateUserBinding

class UpdateUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val sqlUsers = SQLiteCode(this)
        setContentView(binding.root)
        with(binding) {
            btnUpdate.setOnClickListener {
                if (etUpLogin.text.isNotBlank() &&
                    etUpPass.text.isNotBlank() &&
                    etUpName.text.isNotBlank()) {

                    sqlUsers.updateData(
                        selectedUser,
                        "${etUpLogin.text}",
                        "${etUpPass.text}",
                        "${etUpName.text}",
                        "${etUpLogin.text}@mail.ua"
                    )

                    etUpLogin.text.clear()
                    etUpPass.text.clear()
                    etUpName.text.clear()
                }
                else {
                    Toast.makeText(this@UpdateUser, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show()
                }
            }
            btnUpBack.setOnClickListener {
                this@UpdateUser.finish()
            }
        }
    }
}