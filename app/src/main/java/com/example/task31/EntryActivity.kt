package com.example.task31

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task31.db.App
import com.example.task31.models.PonyRaceGenerator
import com.example.task31.db.AppDatabase
import com.example.task31.db.PonyDao
import com.example.task31.models.Pony
import kotlinx.android.synthetic.main.activity_entry.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class EntryActivity : AppCompatActivity() {

    var db: AppDatabase? = App.instance?.database
    private val pony = PonyRaceGenerator().generate(1, 1)
    var dao: PonyDao? = null

    lateinit var preferences: SharedPreferences

    private fun showData(data: List<Pony>?) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, data.toString(), duration)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?   ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        preferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

        dao = AppDatabase.createDb(this).getPonyDao()

        load.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {

                withContext(Dispatchers.IO) {
                    dao?.insert(pony)
                }
            }
            val text = "ОК!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        watchDB.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val data = dao?.getPonies()
                withContext(Main) {
                    showData(data)
                }
            }


            //val duration = Toast.LENGTH_SHORT
            //val toast = Toast.makeText(applicationContext, text, duration)
            //toast.show()
        }

        clear.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                   dao?.delete()
                }
            }
            val text = " OK!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

        logout.setOnClickListener {
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}