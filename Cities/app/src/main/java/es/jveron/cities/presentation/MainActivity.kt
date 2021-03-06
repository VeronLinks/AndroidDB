package es.jveron.cities.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.jveron.cities.R
import es.jveron.cities.presentation.view.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, HomeFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0)
        {
            finish()
        }
    }
}