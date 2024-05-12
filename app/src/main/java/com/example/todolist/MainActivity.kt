package com.example.todolist


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// entity - table
// dao - queries
// database

class MainActivity : AppCompatActivity() {
    private lateinit var database: myDatabase

    private lateinit var addButton: Button
    private lateinit var deleteAllButton: Button
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.add)
        deleteAllButton = findViewById(R.id.deleteAll)
        recyclerView = findViewById(R.id.recycler_view)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        addButton.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
        deleteAllButton.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }

        setRecycler()

    }

    private fun setRecycler() {
        recyclerView.adapter = Adapter(DataObject.getAllData())
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}