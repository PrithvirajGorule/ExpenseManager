package com.example.expensemanager

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.databinding.ActivityMainBinding
import com.example.expensemanager.databinding.ActivitySetLimitsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.processNextEventInCurrentThread
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.exp

@Suppress("DEPRECATION")
open class SetLimits : AppCompatActivity(),OnItemsClick {
    private lateinit var expenseAdapter: ExpenseAdapter
    private lateinit var binding:ActivitySetLimitsBinding
    private var userExpense:Long=0
    public var income:Long=0
    public var expense:Long=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetLimitsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expenseAdapter=ExpenseAdapter(this)
        binding.recycler.setAdapter(expenseAdapter)
        binding.recycler.setLayoutManager(LinearLayoutManager(this))
        val intent=Intent(this@SetLimits,add_expense::class.java)

        binding.addIncome.setOnClickListener{view->
            intent.putExtra("type","Income")
            startActivity(intent)
        }
        binding.addExpense.setOnClickListener{view->
            intent.putExtra("type","Expense")
            startActivity(intent)
        }
    }

    protected override fun onStart() {
        super.onStart()
        val progressDialog=ProgressDialog(this)
        progressDialog.setTitle("Please")
        progressDialog.setMessage("Wait")
        progressDialog.setCancelable(false)
        if(FirebaseAuth.getInstance().currentUser==null){
            progressDialog.show()
            FirebaseAuth.getInstance()
                .signInAnonymously()
                .addOnSuccessListener(OnSuccessListener<AuthResult>{result->
                    progressDialog.cancel()
                })
                .addOnFailureListener(OnFailureListener { e->
                    progressDialog.cancel()
                    Toast.makeText(this@SetLimits,e.message,Toast.LENGTH_SHORT).show()
                })
        }
    }

    protected override fun onResume() {
        super.onResume()
        income=0;
        expense=0;
        getData()
    }

    private fun getData() {
        FirebaseFirestore
            .getInstance()
            .collection("expenses")
            .whereEqualTo("uid",FirebaseAuth.getInstance().uid)
            .get()
            .addOnSuccessListener{queryDocumentSnapshots->
                expenseAdapter.clear()
                val dsList=queryDocumentSnapshots.documents
                for(ds in dsList){
                    val expenseModel=ds.toObject(ExpenseModel::class.java)
                    if(expenseModel?.type.equals("Income")){
                        income+=expenseModel?.amount?.toLong()?:0L
                        DataManger.setIncome(income)
                    }
                    else{
                        expense+=expenseModel?.amount?.toLong()?:0L
                        DataManger.setExpense(expense)
                    }
                   expenseAdapter.add(expenseModel)
                }
                setUpGraph()
            }
    }

    private fun setUpGraph() {
        val pieEntryList=ArrayList<PieEntry>()
        val colorsList=ArrayList<Int>()
        if(income!=0L){
            pieEntryList.add(PieEntry(income.toFloat(),"income"))
            colorsList.add(resources.getColor(R.color.purple_200))
        }
        if(expense!=0L){
            pieEntryList.add(PieEntry(expense.toFloat(),"expense"))
            colorsList.add(resources.getColor(R.color.light_orange))
        }
        val pieDataSet=PieDataSet(pieEntryList,(income+expense).toString())
        pieDataSet.setColors(colorsList)
        val pieData=PieData(pieDataSet)

        binding.piechart.setData(pieData)
        binding.piechart.setDrawEntryLabels(true)
        //binding.piechart.centerText=generateCenterText()
        binding.piechart.description.isEnabled=false
        binding.piechart.legend.isEnabled=false
        binding.piechart.animateY(500,Easing.EaseInOutQuad)
        binding.piechart.invalidate()
    }

    override fun onClick(expenseModel: ExpenseModel?) {

        intent.putExtra("model",expenseModel)
        startActivity(intent)
    }
}