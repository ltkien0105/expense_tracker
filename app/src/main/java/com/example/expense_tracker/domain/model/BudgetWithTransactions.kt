package com.example.expense_tracker.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.expense_tracker.data.local.budget.BudgetEntity
import com.example.expense_tracker.data.local.transaction.TransactionEntity

data class BudgetWithTransactions(
    @Embedded val budget: BudgetEntity,
    @Relation(
        parentColumn = "category",
        entityColumn = "category",
    )
    val transactions: List<TransactionEntity>
)
