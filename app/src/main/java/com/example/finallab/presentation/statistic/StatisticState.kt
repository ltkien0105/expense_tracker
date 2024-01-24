package com.example.finallab.presentation.statistic

import com.example.finallab.data.local.transaction.TransactionEntity

data class StatisticState(
    val tabSelected: Int = 0,
    val rangeSelected: Int = 0,
    val transactions: List<TransactionEntity> = emptyList(),
    val chartData: List<Float> = emptyList(),
)