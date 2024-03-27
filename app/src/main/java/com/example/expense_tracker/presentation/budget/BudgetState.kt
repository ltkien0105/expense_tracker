package com.example.expense_tracker.presentation.budget

import com.example.expense_tracker.domain.model.BudgetItem

data class BudgetState (
    val budgetList: List<BudgetItem> = emptyList(),
    val totalBudget: Double = 0.0,
    val totalSpent: Double = 0.0,
    val dayRemaining: Int = 0
)