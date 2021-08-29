package pl.mradziewicz.notatnik

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.mradziewicz.notatnik.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.toString()
            val message = binding.titleEditText.toString()

            val value = ContentValues()
            value.put("title", title)
            value.put("message", message)

            val dbhelper = DataBaseHelper(applicationContext)
            val db = dbhelper.writableDatabase

            db.insertOrThrow(TableInfo.TABLE_NAME, null, value)

        }
    }
}