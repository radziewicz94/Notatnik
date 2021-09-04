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

class CardViewAdapter(val context: Context, val db: SQLiteDatabase, var note: ArrayList<Note>) :
    RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        val cardView_note = layoutInflate.inflate(R.layout.card_view, parent, false)

        return MyViewHolder(cardView_note)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {

        holder.card_view.setOnClickListener {
            editCardViewWithData(holder)
        }
            //db.delete(TableInfo.TABLE_NAME, null, null)
            holder.title_textView.setText(note[holder.adapterPosition].title)
            holder.message_textView.setText(note[holder.adapterPosition].message)



        holder.card_view.setOnLongClickListener(View.OnLongClickListener {

            Toast.makeText(
                context,
                "notatka została usunięta",
                Toast.LENGTH_SHORT
            ).show()
            db.delete(
                TableInfo.TABLE_NAME, BaseColumns._ID + "=?",
                arrayOf(note[holder.adapterPosition].id.toString())
            )
            note.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)

            true
        })

    }

    private fun editCardViewWithData(holder: MyViewHolder) {
        val intent = Intent(context, DetailsActivity::class.java)
        val titleActivity = note[holder.adapterPosition].title
        val messageActivity = note[holder.adapterPosition].message
        var id_cardView = note[holder.adapterPosition].id.toString()

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