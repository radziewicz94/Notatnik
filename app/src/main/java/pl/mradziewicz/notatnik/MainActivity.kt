package pl.mradziewicz.notatnik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pl.mradziewicz.notatnik.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataBaseHelper = DataBaseHelper(applicationContext)
        val db = dataBaseHelper.writableDatabase
    }

    fun onClickNote(view: View){
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        startActivity(intent)
    }
}