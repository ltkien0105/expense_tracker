package com.example.finallab.presentation.budget

import com.example.finallab.domain.model.BudgetItem

data class BudgetState (
    val budgetList: List<BudgetItem> = emptyList(),
    val totalBudget: Double = 0.0,
    val totalSpent: Double = 0.0,
    val dayRemaining: Int = 0
)