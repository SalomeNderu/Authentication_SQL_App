package com.example.authreg_midmorning

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var edt_email:EditText
    lateinit var edt_password:EditText
    lateinit var btn_login:Button
    lateinit var btn_acc:Button
    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edt_email = findViewById(R.id.email_edt)
        edt_password = findViewById(R.id.password_edt)
        btn_login = findViewById(R.id.login_btn)
        btn_acc = findViewById(R.id.acc_btn)

        db = openOrCreateDatabase("nderuhDB", Context.MODE_PRIVATE, null)

        btn_login.setOnClickListener {
            //write a logic to check if user exists in SQL
            var email = edt_email.text.toString().trim()
            var password = edt_password.text.toString().trim()
            //validate
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Check your fields", Toast.LENGTH_SHORT).show()
            } else {
                //logic to check if user exists in SQL Database
                val cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", arrayOf(email,password))
                if (cursor != null && cursor.moveToFirst()) {
                    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()
                    // user is authenticated, start a new activity
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
            Toast.makeText(this, "Invalid email or password, please try again", Toast.LENGTH_SHORT).show()
        }
            }
        }
        btn_acc.setOnClickListener {
            var gotoregister = Intent(this, MainActivity::class.java)
            startActivity(gotoregister)
        }
    }
}









