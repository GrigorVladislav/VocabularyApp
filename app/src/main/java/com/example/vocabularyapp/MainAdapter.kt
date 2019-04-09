package com.example.vocabularyapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_first_card.view.*
import kotlinx.android.synthetic.main.layout_second_card.view.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class MainAdapter(val answer:ArrayList<String>,
//                  val answerView :View,
                  val answerRes:String,
                  val key:ArrayList<String>,
                  val keyMap:HashMap<String,String>,
                  val context: Context):RecyclerView.Adapter<CustomViewHolder>()
{
    var count:Int = 0
    override fun getItemCount(): Int {
        return 9
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val firstCard = layoutInflater.inflate(R.layout.layout_first_card,parent,false)
        val secondCard= layoutInflater.inflate(R.layout.layout_second_card,parent,false)
        if(count%2==0){
            count++
            firstCard.setOnClickListener{
                firstCardClick(firstCard)

            }
            return CustomViewHolder(firstCard)
        }
        count++
        secondCard.setOnClickListener{
            secondCardClick(secondCard)
        }
        return CustomViewHolder(secondCard)

    }

    private fun secondCardClick(secondCard: View) {
        val secondText =  secondCard.text_second_card.text.toString()
        validate(answerRes,secondText)
    }

    private fun validate( key:String, value:String) {
       val result = keyMap.getValue(key)
        if(result==value){
            Toast.makeText(context,"Correct Answer",Toast.LENGTH_SHORT).show()
        }else
        {Toast.makeText(context,"Wrong Answer",Toast.LENGTH_SHORT).show()}
    }

    private fun firstCardClick(firstCard: View) {
        val firstText= firstCard.text_first_card.text.toString()
        validate(answerRes,firstText)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if(position%2==0){
            holder.view.text_first_card.text = answer[count]
        }else {
            holder.view.text_second_card.text = answer[count]
        }
    }

}

class CustomViewHolder(val view:View): RecyclerView.ViewHolder(view){


}




