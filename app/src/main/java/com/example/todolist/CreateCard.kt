package com.example.todolist


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var saveButton: Button
    private lateinit var titleEditText: EditText
    private lateinit var priorityEditText: EditText
    private lateinit var deadlineEditText: EditText
    private lateinit var descriptionEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        saveButton = findViewById(R.id.save_button)

        saveButton = findViewById(R.id.save_button)
        titleEditText = findViewById(R.id.create_title)
        priorityEditText = findViewById(R.id.create_priority)
        deadlineEditText = findViewById(R.id.create_deadline)
        descriptionEditText = findViewById(R.id.create_description)


        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        saveButton.setOnClickListener {
            if (titleEditText.text.toString().trim { it <= ' ' }.isNotEmpty()
                && priorityEditText.text.toString().trim { it <= ' ' }.isNotEmpty()
                && deadlineEditText.text.toString().trim {it <= ' '}.isNotEmpty()
                && descriptionEditText.text.toString().trim {it <= ' '}.isNotEmpty()
            ) {
                val title = titleEditText.getText().toString()
                val priority = priorityEditText.getText().toString()
                val deadLine = deadlineEditText.getText().toString()
                val description =descriptionEditText.getText().toString()
                DataObject.setData(title, priority,deadLine, description)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority, deadLine, description))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}