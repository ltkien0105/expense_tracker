package com.example.expense_tracker.data.local.transaction

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table ORDER BY date DESC")
    fun getAll(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transaction_table WHERE id = :id")
    fun getById(id: Int): Flow<TransactionEntity?>

    @Query("SELECT * FROM transaction_table ORDER BY date DESC LIMIT :quantity")
    fun getTransactionsByQuantity(quantity: Int): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transaction_table ORDER BY amount DESC")
    fun getTransactionsOrderedByAmount(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transaction_table ORDER BY date DESC")
    fun getTransactionsOrderedByDate(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transaction_table WHERE datetime(date) BETWEEN datetime(:startDate) AND datetime(:endDate)")
    fun getTransactionsInPeriod(startDate: String, endDate: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transaction_table WHERE type = 'Expense' ORDER BY date DESC")
    fun getExpenseTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT SUM(amount) FROM transaction_table WHERE type = 'Expense'")
    fun getTotalAmountExpense(): Flow<Double>

    @Query("SELECT SUM(amount) FROM transaction_table WHERE type = 'Expense' AND category = :category AND datetime(date) BETWEEN datetime(:startDate) AND datetime(:endDate)")
    fun getSumAmountExpenseByCategoryInPeriod(category: String, startDate: String, endDate: String): Flow<Double>

    @Query("SELECT SUM(amount) FROM transaction_table WHERE type = 'Income'")
    fun getTotalAmountIncome(): Flow<Double>

    @Query("SELECT * FROM transaction_table WHERE type = 'Income' ORDER BY date DESC")
    fun getIncomeTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transaction_table WHERE category = :category ORDER BY date DESC")
    fun getTransactionsByCategory(category: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transaction_table WHERE type = :type AND datetime(date) BETWEEN datetime(:startDate) AND datetime(:endDate)")
    fun getTransactionsByTypeAndPeriod(type: String, startDate: String, endDate: String): Flow<List<TransactionEntity>>

    @Query("DELETE FROM transaction_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionEntity: TransactionEntity)

    @Update
    suspend fun update(transactionEntity: TransactionEntity)

    @Delete
    suspend fun deleteOne(transactionEntity: TransactionEntity)
}