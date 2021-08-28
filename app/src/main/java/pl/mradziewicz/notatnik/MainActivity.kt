package pl.mradziewicz.notatnik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataBaseHelper = DataBaseHelper(applicationContext)
        val db = dataBaseHelper.writableDatabase
    }
}