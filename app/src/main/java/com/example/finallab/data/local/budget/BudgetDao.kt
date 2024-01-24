package com.example.finallab.data.local.budget

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.finallab.domain.model.BudgetWithTransactions
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Query("SELECT * FROM budget_table")
    fun getAllBudgets(): Flow<List<BudgetEntity>>

    @Query("SELECT * FROM budget_table WHERE id = :id")
    fun getBudgetById(id: Int): Flow<BudgetEntity?>

    @Query("SELECT * FROM budget_table WHERE category = :category")
    fun getBudgetByCategory(category: String): Flow<BudgetEntity?>

    @Query("SELECT * FROM budget_table WHERE datetime(start_time) >= datetime(:startTime) AND datetime(end_time) <= datetime(:endTime)")
    fun getBudgetInPeriod(startTime: String, endTime: String): Flow<BudgetEntity?>

    @Query("SELECT SUM(total_amount) FROM budget_table")
    fun getTotalBudget(): Flow<Double>

    @Transaction
    @Query("SELECT * FROM budget_table ")
    fun getBudgetAndListExpensesTransaction(): Flow<List<BudgetWithTransactions>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(budgetEntity: BudgetEntity)

    @Update
    suspend fun update(budgetEntity: BudgetEntity)
}