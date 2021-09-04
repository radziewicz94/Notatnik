package pl.mradziewicz.notatnik

import android.content.ContentValues
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import pl.mradziewicz.notatnik.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleEditText.setText(intent.getStringExtra("title"))
        binding.messageDetailsEditText.setText(intent.getStringExtra("message"))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dbhelper = DataBaseHelper(applicationContext)
        val db = dbhelper.writableDatabase
        if(item.itemId == R.id.save_menu){
            if (intent.hasExtra("ID")) {
                updateCardView(db)
            } else {
                enterNewCardView(db)
            }
        }
        db.close()
        onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    private fun enterNewCardView(db: SQLiteDatabase) {
        val title = binding.titleEditText.text.toString()
        val message = binding.messageDetailsEditText.text.toString()
        if (message != "" || title != "") {
            val value = ContentValues()
            value.put("title", title)
            value.put("message", message)

            db.insert(TableInfo.TABLE_NAME, null, value)
            Toast.makeText(
                applicationContext,
                "Notatka została zapisana, możesz wyjść!",
                Toast.LENGTH_LONG
            ).show()
        } else{
            Toast.makeText(
                applicationContext,
                "Tytuł i Treśc są puste, nie mam co zapisać", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateCardView(db: SQLiteDatabase) {
        val value = ContentValues()
        val id = intent.getStringExtra("ID").toString()
        val title = binding.titleEditText.text.toString()
        val message = binding.messageDetailsEditText.text.toString()

        value.put("title", title)
        value.put("message", message)
        db.update(TableInfo.TABLE_NAME, value, BaseColumns._ID + "=?", arrayOf(id))
        Toast.makeText(
            applicationContext,
            "Notatka zaktualizowana, możesz wyjść!",
            Toast.LENGTH_LONG
        ).show()
    }


}