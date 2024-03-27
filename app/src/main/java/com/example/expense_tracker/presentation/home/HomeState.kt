package com.example.expense_tracker.presentation.home

import com.example.expense_tracker.data.local.transaction.TransactionEntity

data class HomeState(
    val income: Double = 0.0,
    val expense: Double = 0.0,
    val transactions: List<TransactionEntity> = emptyList()
)
