package com.example.todolist

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


class Adapter(private var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val priority = itemView.findViewById<TextView>(R.id.priority)
        val deadLine = itemView.findViewById<TextView>(R.id.deadLine)
        val description = itemView.findViewById<TextView>(R.id.description)
        val layout = itemView.findViewById<LinearLayout>(R.id.mylayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (data[position].priority.lowercase(Locale.getDefault())) {
            "high" -> {
                holder.layout.setBackgroundColor(Color.parseColor("#FFB8B1"))
                holder.title.setTextColor(Color.WHITE)
                holder.priority.setTextColor(Color.WHITE)
            }

            "medium" -> {
                holder.layout.setBackgroundColor(Color.parseColor("#FDD790"))
                holder.title.setTextColor(Color.WHITE)
                holder.priority.setTextColor(Color.WHITE)
            }

            else -> {
                holder.layout.setBackgroundColor(Color.parseColor("#95D4A3"))
                holder.title.setTextColor(Color.WHITE)
                holder.priority.setTextColor(Color.WHITE)
            }
        }

        holder.title.text = data[position].title
        holder.priority.text = data[position].priority
        holder.deadLine.text = data[position].deadLine
        holder.description.text = data[position].description
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCard::class.java)
            intent.putExtra("id", position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}