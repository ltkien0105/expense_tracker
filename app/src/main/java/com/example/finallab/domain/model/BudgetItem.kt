package com.example.finallab.domain.model

import java.time.LocalDateTime

data class BudgetItem(
    val id: Int,
    val category: String,
    val totalAmount: Double,
    val leftAmount: Double,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)
