package com.example.finallab.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.finallab.R
import com.example.finallab.data.local.transaction.TransactionType

enum class CategoriesItem(
    @DrawableRes val icon: Int,
    @StringRes val category: Int,
    val type: TransactionType
) {
    Allowance(
        R.drawable.ic_allowance,
        R.string.allowance,
        TransactionType.Income
    ),
    Beauty(
        R.drawable.ic_beauty,
        R.string.beauty,
        TransactionType.Expense
    ),
    Bill(
        R.drawable.ic_bill,
        R.string.bill,
        TransactionType.Expense
    ),
    Donation(
        R.drawable.ic_donation,
        R.string.donation,
        TransactionType.Expense
    ),
    Education(
        R.drawable.ic_education,
        R.string.education,
        TransactionType.Expense
    ),
    Family(
        R.drawable.ic_family,
        R.string.family,
        TransactionType.Expense
    ),
    Food(
        R.drawable.ic_food,
        R.string.food,
        TransactionType.Expense
    ),
    Gift(
        R.drawable.ic_gift,
        R.string.gift,
        TransactionType.Expense
    ),
    Groceries(
        R.drawable.ic_groceries,
        R.string.groceries,
        TransactionType.Expense
    ),
    Health(
        R.drawable.ic_health,
        R.string.health,
        TransactionType.Expense
    ),
    Home(
        R.drawable.ic_home,
        R.string.home,
        TransactionType.Expense
    ),
    Leisure(
        R.drawable.ic_leisure,
        R.string.leisure,
        TransactionType.Expense
    ),
    OtherExpense(
        R.drawable.ic_other_expense,
        R.string.other_expense,
        TransactionType.Expense
    ),
    OtherIncome(
        R.drawable.ic_other_income,
        R.string.other_income,
        TransactionType.Income
    ),
    Pets(
        R.drawable.ic_pets,
        R.string.pets,
        TransactionType.Expense
    ),
    Salary(
        R.drawable.ic_salary,
        R.string.salary,
        TransactionType.Income
    ),
    Transport(
        R.drawable.ic_transport,
        R.string.transport,
        TransactionType.Expense
    ),
    Travel(
        R.drawable.ic_travel,
        R.string.travel,
        TransactionType.Expense
    ),
}