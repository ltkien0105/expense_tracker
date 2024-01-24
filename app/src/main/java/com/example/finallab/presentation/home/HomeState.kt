package com.example.finallab.presentation.home

import com.example.finallab.data.local.transaction.TransactionEntity

data class HomeState(
    val income: Double = 0.0,
    val expense: Double = 0.0,
    val transactions: List<TransactionEntity> = emptyList()
)
