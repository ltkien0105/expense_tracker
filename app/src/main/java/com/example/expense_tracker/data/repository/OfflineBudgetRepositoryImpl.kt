package com.example.expense_tracker.data.repository

import com.example.expense_tracker.data.local.budget.BudgetDao
import com.example.expense_tracker.data.local.budget.BudgetEntity
import com.example.expense_tracker.domain.model.BudgetWithTransactions
import com.example.expense_tracker.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineBudgetRepositoryImpl @Inject constructor(
    private val budgetDao: BudgetDao
): BudgetRepository {
    override fun getAllBudgets(): Flow<List<BudgetEntity>> = budgetDao.getAllBudgets()

    override fun getBudgetById(id: Int): Flow<BudgetEntity?> = budgetDao.getBudgetById(id)

    override fun getBudgetByCategory(category: String): Flow<BudgetEntity?>
        = budgetDao.getBudgetByCategory(category)

    override fun getTotalBudget(): Flow<Double>
        = budgetDao.getTotalBudget()

    override fun getBudgetAndListExpensesTransaction(): Flow<List<BudgetWithTransactions>>
        = budgetDao.getBudgetAndListExpensesTransaction()

    override fun getBudgetInPeriod(startTime: String, endTime: String): Flow<BudgetEntity?>
        = budgetDao.getBudgetInPeriod(startTime, endTime)

    override suspend fun insert(budgetEntity: BudgetEntity)
        = budgetDao.insert(budgetEntity)

    override suspend fun update(budgetEntity: BudgetEntity) = budgetDao.update(budgetEntity)
}