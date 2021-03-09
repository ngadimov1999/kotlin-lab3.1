package com.example.task31

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX", false)

        if (isRemembered){
            val intent = Intent(this, EntryActivity::class.java)
            startActivity(intent)
            finish()
        }

        login.setOnClickListener{
            val email: String = email.text.toString()
            val pass: String = pass.text.toString()
            val checked: Boolean = checkBox.isChecked

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("EMAIL", email)
            editor.putString("PASS", pass)
            editor.putBoolean("CHECKBOX", checked)

            editor.apply()

            Toast.makeText(this, "Данные улетели в америку, в ЦРУ", Toast.LENGTH_LONG).show()

            val intent = Intent(this, EntryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}