package pl.mradziewicz.notatnik

import android.app.Service
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.content.ClipboardManager as ClipboardManager

class CardViewAdapter(val context: Context, val db: SQLiteDatabase) :
    RecyclerView.Adapter<MyViewHolder>() {
//    val dbHelper = DataBaseHelper(context)
//    val db = dbHelper.readableDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        val cardView_note = layoutInflate.inflate(R.layout.card_view, parent, false)

        return MyViewHolder(cardView_note)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val cursor = db.query(
            TableInfo.TABLE_NAME,
            null,
            BaseColumns._ID + "=?",
            arrayOf(holder.adapterPosition.plus(1).toString()),
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            holder.title_textView.text = cursor.getString(1)
            holder.message_textView.text = cursor.getString(2)
        }

        holder.card_view.setOnLongClickListener(View.OnLongClickListener {
//            Toast.makeText(context, "Skopiowane do schowka", Toast.LENGTH_SHORT).show()
//            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            val clipData = ClipData.newPlainText("CopyText", "Title: "
//                    + holder.title_textView.text + "\nMessage : ${holder.message_textView.text}")
//            cm.setPrimaryClip(clipData)
            val id = holder.adapterPosition.plus(1)
            Toast.makeText(context, "karta została usunięta", Toast.LENGTH_SHORT)
                .show()
            db.delete(TableInfo.TABLE_NAME, BaseColumns._ID + "=?", arrayOf(id.toString()))
            notifyItemRemoved(id)
            true
        })

        holder.card_view.setOnClickListener {
            editCardViewWithData(holder)
        }

    }

    private fun editCardViewWithData(holder: MyViewHolder) {
        val intent = Intent(context, DetailsActivity::class.java)
        val titleActivity = holder.title_textView.text.toString()
        val messageActivity = holder.message_textView.text.toString()
        var id_cardView = holder.adapterPosition.plus(1).toString()

        intent.putExtra("title", titleActivity)
        intent.putExtra("message", messageActivity)
        intent.putExtra("ID", id_cardView)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        val cursor = db.query(TableInfo.TABLE_NAME, null, null, null, null, null, null)
        return cursor.count
    }
}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var title_textView: TextView = view.findViewById(R.id.title_textView)
    var message_textView: TextView = view.findViewById(R.id.message_textView)
    val card_view: CardView = view.findViewById(R.id.note_cardView)
}