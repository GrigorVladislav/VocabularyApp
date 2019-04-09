package com.example.vocabularyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_first_card.view.*
import kotlinx.android.synthetic.main.layout_second_card.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val wordToDefn= java.util.HashMap<String, String>()
    private val words=ArrayList<String>()
    private val answer=ArrayList<String>()
    lateinit var word:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        readDictionaryFile()
        setupList()


     }

    private fun checkAnswer() {
        recyclerView_main.text_first_card.text
    }

    private fun readDictionaryFile() {
        val reader = Scanner(resources.openRawResource(R.raw.getword),"utf-8")
        while (reader.hasNextLine()) {
            val line=reader.nextLine()
            val pieces = line.split(':')
            answer.add(pieces[0])
            words.add(pieces[1])
            wordToDefn.put(pieces[0], pieces[1])
            Log.d("$pieces[0]","$pieces[1]")
        }

    }
    private fun setupList(){
        val rand = Random()
        val index =rand.nextInt(9)
        word =answer[index]
        answer_textview.text=word
        answer.shuffle()
    }

    override fun onResume() {
        super.onResume()
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = MainAdapter(words,word,answer,wordToDefn,this)
        recyclerView_main.setOnClickListener {
            checkAnswer()
        }
    }
}





