package es.ua.eps.sqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.ua.eps.sqlite.databinding.ActivityNewUserBinding

class NewUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            btnNewBack.setOnClickListener {
                this@NewUser.finish()
            }
            btnNewUser.setOnClickListener {
                if (etNewLogin.text.isNotBlank() &&
                    etNewPass.text.isNotBlank() &&
                    etNewName.text.isNotBlank()) {

                    val cv = ContentValues()
                    cv.put(ProviderCode.NOMBRE_USUARIO,"${etNewLogin.text}")
                    cv.put(ProviderCode.PASSWORD,"${etNewPass.text}")
                    cv.put(ProviderCode.NOMBRE_COMPLETO,"${etNewName.text}")
                    cv.put(ProviderCode.EMAIL,"${etNewLogin.text}@mail.ua")
                    contentResolver.insert(ProviderCode.CONTENT_URI,cv)

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