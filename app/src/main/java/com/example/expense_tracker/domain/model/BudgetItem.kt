package com.example.expense_tracker.domain.model

import java.time.LocalDateTime

data class BudgetItem(
    val id: Int,
    val category: String,
    val totalAmount: Double,
    val leftAmount: Double,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)
