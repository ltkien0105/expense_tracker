package com.example.expense_tracker.presentation.statistic

import com.example.expense_tracker.data.local.transaction.TransactionEntity

data class StatisticState(
    val tabSelected: Int = 0,
    val rangeSelected: Int = 0,
    val transactions: List<TransactionEntity> = emptyList(),
    val chartData: List<Float> = emptyList(),
)