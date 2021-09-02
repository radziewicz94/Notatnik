package pl.mradziewicz.notatnik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import pl.mradziewicz.notatnik.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    fun onClickNote(view: View){
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        Toast.makeText(applicationContext, "Przechodzę do notatek", Toast.LENGTH_LONG)
            .show()
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val dataBaseHelper = DataBaseHelper(applicationContext)
        val db = dataBaseHelper.writableDatabase

        binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.recyclerView.adapter = CardViewAdapter(applicationContext, db)
    }
}