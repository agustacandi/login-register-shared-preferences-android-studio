package agustacandi.lrsharedpref

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonLogin = findViewById<Button>(R.id.login_btn)
        val buttonRegister = findViewById<Button>(R.id.register_btn)
        val edUsername = findViewById<EditText>(R.id.txt_username)
        val edPassword = findViewById<EditText>(R.id.txt_password)

        buttonLogin.setOnClickListener {
            val prefs = getSharedPreferences("MY_PREFS", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val username = edUsername.text.toString()
            val password = edPassword.text.toString()
            var isValid = false;
            val data = prefs.getStringSet("USER", null)
            if(prefs.contains("USER")) {
                for (item in data!!.iterator()) {
                    val user = item.split(",")
                    if(user[1] == username.lowercase() && user[3] == password) {
                        editor.putString("SESSION_NAME", user[0])
                        editor.putString("SESSION_USERNAME", user[1])
                        editor.putString("SESSION_EMAIL", user[2])
                        editor.putString("SESSION_PASSWORD", user[3])
                        editor.apply()
                        isValid = true
                    }
                }
                if (isValid) {
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(applicationContext, "Username or password invalid", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "User not found", Toast.LENGTH_SHORT).show()
            }
        }

        buttonRegister.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
    }
}