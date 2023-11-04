
package com.example.expensemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.NotificationCompat.getCategory
import com.example.expensemanager.databinding.ActivityAddExpenseBinding
import com.example.expensemanager.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import java.lang.Long
import java.math.RoundingMode.valueOf
import kotlin.math.exp

@Suppress("DEPRECATION")
class add_expense : AppCompatActivity() {
    private lateinit var type:String
    private lateinit var binding: ActivityAddExpenseBinding
    private lateinit var expenseModel: ExpenseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        type= intent.getStringExtra("type").toString()

        if(type==null) {
            type = expenseModel.type
            binding.amount.setText(expenseModel.amount.toString())
            binding.category.setText(expenseModel.category)
            binding.note.setText(expenseModel.note)
        }

        if(type.equals("Income")){
            binding.IncomeRadio.setChecked(true)
        }
        else{
            binding.ExpenseRadio.setChecked(true)
        }

        binding.IncomeRadio.setOnClickListener { view->
            type="Income";
        }
        binding.ExpenseRadio.setOnClickListener { view->
            type="Expense";
        }
    }

    public override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(type==null) {
            menuInflater.inflate(R.menu.add_menu, menu)
        }
        else{
            menuInflater.inflate(R.menu.update_menu,menu)
        }
        return true
    }

    public override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==R.id.saveExpense)
        {
            if(type!=null){
                createExpense();
            }
            else{
                updateExpense();
            }
            return true;
        }
        if(id==R.id.deleteExpense){
            deleteExpense()
        }
        return false;
    }

    private fun deleteExpense() {
        FirebaseFirestore
            .getInstance()
            .collection("expenses")
            .document(expenseModel.expenseId)
            .delete()
        finish()
    }

    private fun createExpense() {
        val expenseId=UUID.randomUUID().toString()
        val amount=binding.amount.text.toString()
        val note=binding.note.text.toString()
        val category=binding.category.text.toString()

        val incomeChecked: Boolean = binding.IncomeRadio.isChecked()
        if(incomeChecked){
            type="Income";
        }
        else{
            type="Expenses"
        }
        if(amount.trim().length==0){
            binding.amount.setError("Empty")
            return
        }
        val expenseModel=ExpenseModel(expenseId,note,category,type,amount.toLong(),Calendar.getInstance().timeInMillis,FirebaseAuth.getInstance().uid)
        FirebaseFirestore
            .getInstance()
            .collection("expenses")
            .document(expenseId)
            .set(expenseModel)
        finish()
    }
    private fun updateExpense() {
        val expenseId=expenseModel.expenseId
        val amount=binding.amount.text.toString()
        val note=binding.note.text.toString()
        val category=binding.category.text.toString()

        val incomeChecked: Boolean = binding.IncomeRadio.isChecked()
        if(incomeChecked){
            type="Income";
        }
        else{
            type="Expenses"
        }
        if(amount.trim().length==0){
            binding.amount.setError("Empty")
            return
        }
        val model=ExpenseModel(expenseId,note,category,type,amount.toLong(),expenseModel.time,FirebaseAuth.getInstance().uid)
        FirebaseFirestore
            .getInstance()
            .collection("expenses")
            .document(expenseId)
            .set(model)
        finish()
    }
}
