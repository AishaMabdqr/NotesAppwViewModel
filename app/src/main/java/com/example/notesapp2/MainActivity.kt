package com.example.notesapp2

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rvItems : RecyclerView
    lateinit var eInput : EditText
    lateinit var bAdd : Button
    lateinit var rvAdapter: RVAdapter


     val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewModel.getNotes().observe(this, {
            notes -> rvAdapter.update(notes)
        })

        rvItems = findViewById(R.id.rvItems)
        eInput = findViewById(R.id.eInput)
        bAdd = findViewById(R.id.bAdd)

        bAdd.setOnClickListener{
            myViewModel.addNote(eInput.text.toString())
            eInput.clearFocus()
           eInput.text.clear()
        }

        rvAdapter = RVAdapter(this)
        rvItems.adapter = rvAdapter
        rvItems.layoutManager = LinearLayoutManager(this)
    }

    fun dialog(pk : Int){
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new note"

        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ -> myViewModel.updateNote(pk, updatedNote.text.toString())

            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }
}