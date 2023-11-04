package com.example.expensemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter(private val context:Context):RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {
    private val expenseList:MutableList<ExpenseModel> = mutableListOf()
    private val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val noteTextView:TextView=itemView.findViewById(R.id.note)
        val categoryTextView:TextView=itemView.findViewById(R.id.category)
        val dateTextView:TextView=itemView.findViewById(R.id.date)
        val amountTextView:TextView=itemView.findViewById(R.id.amount)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.expense_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun add(expenseModel:ExpenseModel?){
        if(expenseModel!=null){
            expenseList.add(expenseModel)
            notifyDataSetChanged()
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expenseModel=expenseList[position]
        holder.noteTextView.text=expenseModel.note
        holder.categoryTextView.text=expenseModel.category
        holder.amountTextView.text=expenseModel.amount.toString()
    }

    fun clear()
    {
        expenseList.clear()
        notifyDataSetChanged()
    }
    private fun getFormattedDate(date: String): String {
        return if(date!=null) sdf.format(Date(date)) else ""
    }
}