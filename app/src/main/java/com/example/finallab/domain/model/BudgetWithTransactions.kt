package com.example.finallab.domain.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.finallab.data.local.budget.BudgetEntity
import com.example.finallab.data.local.transaction.TransactionEntity

data class BudgetWithTransactions(
    @Embedded val budget: BudgetEntity,
    @Relation(
        parentColumn = "category",
        entityColumn = "category",
    )
    val transactions: List<TransactionEntity>
)
