package com.example.pr15

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class firstquest : AppCompatActivity() {
    private lateinit var login: EditText
    private lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firstquest)
        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
    }

    fun gotoquest(view: View) {
        if(login.text.toString().isNotEmpty() && password.text.toString().isNotEmpty())
        {
            if(login.text.toString() == getString(R.string.corlogin))
            {
                if(password.text.toString() == getString(R.string.corpass))
                {
                    val intent = Intent(this,First::class.java)
                    startActivity(intent)
                }
                else
                {
                    val toast = Toast.makeText(this,getString(R.string.wrongpass),Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER,0,0)
                    toast.show()
                }

            }
            else
            {
                val toast = Toast.makeText(this,getString(R.string.wronglog),Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
            }

        }
        else
        {
            val toast = Toast.makeText(this,getString(R.string.emptybody),Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }

    }
}