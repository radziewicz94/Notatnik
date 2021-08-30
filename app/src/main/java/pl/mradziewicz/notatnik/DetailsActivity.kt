package pl.mradziewicz.notatnik

import android.content.ContentValues
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import pl.mradziewicz.notatnik.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbhelper = DataBaseHelper(applicationContext)
        val db = dbhelper.writableDatabase

            binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.toString()
            val message = binding.titleEditText.toString()

            val value = ContentValues().apply {
                put("title", title)
                put("message", message)
            }
                Toast.makeText(applicationContext, "Notatka została zapisana, możesz wyjść!", Toast.LENGTH_LONG)
                    .show()
            db.insert(TableInfo.TABLE_NAME, null, value)
                val count = DatabaseUtils.queryNumEntries(db, TableInfo.TABLE_NAME)
                println(count)

        }
    }


}