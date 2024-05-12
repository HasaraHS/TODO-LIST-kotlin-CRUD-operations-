package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase

    private lateinit var createTitle: EditText
    private lateinit var createPriority: EditText
    private lateinit var createDeadline: EditText
    private lateinit var createDescription: EditText
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {

            createTitle = findViewById(R.id.create_title)
            createPriority = findViewById(R.id.create_priority)
            createDeadline = findViewById(R.id.create_deadline)
            createDescription = findViewById(R.id.create_description)
            deleteButton = findViewById(R.id.delete_button)
            updateButton = findViewById(R.id.update_button)

            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            val deadLine = DataObject.getData(pos).deadLine
            val description = DataObject.getData(pos).description

            createTitle.setText(title)
            createPriority.setText(priority)
            createDeadline.setText(deadLine)
            createDescription.setText(description)

            deleteButton.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            createTitle.text.toString(),
                            createPriority.text.toString() ,
                            createDeadline.text.toString() ,
                            createDescription.text.toString()

                        )
                    )
                }
                myIntent()
            }

            updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    createTitle.text.toString(),
                    createPriority.text.toString() ,
                    createDeadline.text.toString() ,
                    createDescription.text.toString()
                )

                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1,
                            createTitle.text.toString(),
                            createPriority.text.toString(),
                            createDeadline.text.toString(),
                            createDescription.text.toString()
                        )
                    )
                }
                myIntent()
            }

        }
    }

     fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}