package es.ua.eps.sqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import es.ua.eps.sqlite.databinding.ActivityUpdateUserBinding

class UpdateUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnUpdate.setOnClickListener {
                if (etUpLogin.text.isNotBlank() &&
                    etUpPass.text.isNotBlank() &&
                    etUpName.text.isNotBlank()) {

                    val cv = ContentValues()
                    cv.put(ProviderCode.NOMBRE_USUARIO,"${etUpLogin.text}")
                    cv.put(ProviderCode.PASSWORD,"${etUpPass.text}")
                    cv.put(ProviderCode.NOMBRE_COMPLETO,"${etUpName.text}")
                    cv.put(ProviderCode.EMAIL,"${etUpLogin.text}@mail.ua")
                    contentResolver.update(ProviderCode.CONTENT_URI,cv,"${ProviderCode.NOMBRE_USUARIO} = ?", arrayOf(selectedUser))

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