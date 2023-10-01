package com.example.task3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OlympicDataAdapter(private val dataList: List<OlympicData>, private val context: Context) :
    RecyclerView.Adapter<OlympicDataAdapter.ViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val subtitleTextView: TextView = itemView.findViewById(R.id.textViewSubtitle)
        val iconImageView: ImageView = itemView.findViewById(R.id.imageViewIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        holder.titleTextView.text = item.country
        holder.subtitleTextView.text = "Medals: ${item.goldMedals}G ${item.silverMedals}S ${item.bronzeMedals}B"

        if (item.goldMedals >= 5) {
            holder.iconImageView.setImageResource(R.drawable.gold_medal)
        } else if (item.silverMedals >= 5) {
            holder.iconImageView.setImageResource(R.drawable.silver_medal)
        } else if (item.bronzeMedals >= 5) {
            holder.iconImageView.setImageResource(R.drawable.bronze_medal)
        } else {
            holder.iconImageView.setImageResource(R.drawable.default_icon)
        }

        holder.itemView.setOnClickListener {
            val item = dataList[position]
            itemClickListener?.onItemClick(item)

            val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("lastClickedCountry", item.country)
            editor.apply()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
}

interface OnItemClickListener {
    fun onItemClick(item: OlympicData)
}
