package com.example.finallab.domain.repository

import com.example.finallab.data.local.budget.BudgetEntity
import com.example.finallab.data.local.transaction.TransactionEntity
import com.example.finallab.domain.model.BudgetWithTransactions
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    fun getAllBudgets(): Flow<List<BudgetEntity>>

    fun getBudgetById(id: Int): Flow<BudgetEntity?>

    fun getBudgetByCategory(category: String): Flow<BudgetEntity?>

    fun getBudgetInPeriod(startTime: String, endTime: String): Flow<BudgetEntity?>

    fun getTotalBudget(): Flow<Double>

    fun getBudgetAndListExpensesTransaction(): Flow<List<BudgetWithTransactions>>

    suspend fun insert(budgetEntity: BudgetEntity)

    suspend fun update(budgetEntity: BudgetEntity)
}