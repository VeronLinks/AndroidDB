package es.jveron.practica101.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.jveron.practica101.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragmentHost,
            HomeFragment()).commit()
    }
}