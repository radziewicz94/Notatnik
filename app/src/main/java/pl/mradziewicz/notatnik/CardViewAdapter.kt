package pl.mradziewicz.notatnik

import android.content.Context
import android.database.DatabaseUtils
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CardViewAdapter(val context: Context): RecyclerView.Adapter<MyViewHolder>() {
    val dbHelper = DataBaseHelper(context)
    val db = dbHelper.readableDatabase
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        val cardView_note = layoutInflate.inflate(R.layout.card_view, parent, false)

        return MyViewHolder(cardView_note)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val cursor = db.query(TableInfo.TABLE_NAME, null, null, null, null, null, null)
        while (cursor.moveToNext()){
            val getId = cursor.getColumnIndex(TableInfo.TABLE_COLUMN_TITLE).plus(1)
            cursor.getColumnIndexOrThrow(BaseColumns._ID)
            holder.title.text = cursor.getString(getId)
        }

        holder.title
    }

    override fun getItemCount(): Int {

        return 30
    }
}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    val title: TextView = view.findViewById(R.id.title_textView)
    val message: TextView = view.findViewById(R.id.message_textView)
    val card_view: CardView = view.findViewById(R.id.note_cardView)
}