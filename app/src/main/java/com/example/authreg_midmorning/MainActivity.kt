package com.example.authreg_midmorning

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var first_edit: EditText
    lateinit var second_edit: EditText
    lateinit var email_edit: EditText
    lateinit var password_edit: EditText
    lateinit var create_btn: Button
    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        first_edit = findViewById(R.id.edit_firstname)
        second_edit = findViewById(R.id.edit_secondname)
        email_edit = findViewById(R.id.edit_email)
        password_edit = findViewById(R.id.edit_password)
        create_btn = findViewById(R.id.createacc_btn)
        db = openOrCreateDatabase("nderuhDB", Context.MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS users(firstname VARCHAR, secondname VARCHAR, arafa VARCHAR, pass VARCHAR)")
        create_btn.setOnClickListener {
            var firstname_edit = first_edit.text.toString().trim()
            var secondname_edit = second_edit.text.toString().trim()
            var arafa_edit = email_edit.text.toString().trim()
            var pass_edit = password_edit.text.toString().trim()
            //validate your edit texts
            if (firstname_edit.isEmpty() || secondname_edit.isEmpty() || arafa_edit.isEmpty() || pass_edit.isEmpty()) {
                Toast.makeText(this, "Cannot Submit An Empty Field", Toast.LENGTH_SHORT).show()
            } else {
                //Insert Data
                db.execSQL("INSERT INTO users VALUES( '\"+firstname_edit+\"', '\"+secondname_edit+\"', '\"+arafa_edit+\"',  '\"+pass_edit+\"')")
                Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

