package com.example.expense_tracker.domain.repository

import com.example.expense_tracker.data.local.transaction.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAll(): Flow<List<TransactionEntity>>

    fun getById(id: Int): Flow<TransactionEntity?>

    fun deleteAll()

    fun getTransactionsByQuantity(quantity: Int): Flow<List<TransactionEntity>>

    fun getTransactionsOrderedByAmount(): Flow<List<TransactionEntity>>

    fun getTransactionsOrderedByDate(): Flow<List<TransactionEntity>>

    fun getTransactionsInPeriod(startDate: String, endDate: String): Flow<List<TransactionEntity>>

    fun getSumAmountExpenseByCategoryInPeriod(category: String, startDate: String, endDate: String): Flow<Double>

    fun getExpenseTotalAmount(): Flow<Double>

    fun getIncomeTotalAmount(): Flow<Double>

    fun getExpenseTransactions(): Flow<List<TransactionEntity>>

    fun getIncomeTransactions(): Flow<List<TransactionEntity>>

    fun getTransactionsByTypeAndPeriod(type: String, startDate: String, endDate: String): Flow<List<TransactionEntity>>

    suspend fun insert(transactionEntity: TransactionEntity)

    suspend fun update(transactionEntity: TransactionEntity)

    suspend fun deleteOne(transactionEntity: TransactionEntity)
}