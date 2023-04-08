package agustacandi.lrsharedpref

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerBtn = findViewById<Button>(R.id.register_btn)

        val edName = findViewById<EditText>(R.id.txt_name)
        val edUsername = findViewById<EditText>(R.id.txt_username)
        val edEmail = findViewById<EditText>(R.id.txt_email)
        val edPassword = findViewById<EditText>(R.id.txt_password)

        val prefs = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)

        val dataUser = mutableSetOf<String>()

        val dataUserFromPrefs = prefs.getStringSet("USER", null)



        registerBtn.setOnClickListener {
            val name = edName.text.toString()
            val username = edUsername.text.toString().lowercase()
            val email = edEmail.text.toString().lowercase()
            val password = edPassword.text.toString()

            if (!dataUserFromPrefs.isNullOrEmpty()) {
                dataUserFromPrefs.forEach {
                    dataUser.add(it)
                }
                dataUser.add("$name,$username,$email,$password")
            } else {
                dataUser.add("$name,$username,$email,$password")
            }

            val editor = prefs.edit()
            editor.putStringSet("USER", dataUser)
            editor.apply()
            finish()
        }
    }
}