package com.example.expensemanager

object DataManger {
    private var income: Long = 0
    private var expense: Long = 0

    fun setIncome(income: Long) {
        this.income = income
    }

    fun setExpense(expense: Long) {
        this.expense = expense
    }

    fun getIncome(): Long {
        return income
    }

    fun getExpense(): Long {
        return expense
    }
}