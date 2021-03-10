package com.example.task31

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.task31.db.App
import com.example.task31.models.PonyRaceGenerator
import com.example.task31.db.AppDatabase
import com.example.task31.db.PonyDao
import kotlinx.android.synthetic.main.activity_entry.*


class EntryActivity : AppCompatActivity() {

    var dao : PonyDao? = null

    lateinit var preferences: SharedPreferences

    var db: AppDatabase? = App.instance?.database
    val pony = PonyRaceGenerator().generate(1,1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = AppDatabase.createDb(requireContext()).getPonyDao()
        setContentView(R.layout.activity_entry)
        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        load.setOnClickListener{
            dao!!.insert(pony)
            val text = "ОК!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        watchDB.setOnClickListener{
            var text = db.toString()
            if (db == null){
                text = "Пусто!"
            }
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        clear.setOnClickListener{
            var text = "OK!"

            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        logout.setOnClickListener{
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}