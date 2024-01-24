package com.example.finallab.data.repository

import com.example.finallab.data.local.transaction.TransactionDao
import com.example.finallab.data.local.transaction.TransactionEntity
import com.example.finallab.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineTransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {
    override fun getAll(): Flow<List<TransactionEntity>> = transactionDao.getAll()

    override fun getById(id: Int): Flow<TransactionEntity?> = transactionDao.getById(id)

    override fun deleteAll() = transactionDao.deleteAll()

    override fun getTransactionsByQuantity(quantity: Int): Flow<List<TransactionEntity>>
        = transactionDao.getTransactionsByQuantity(quantity)

    override fun getTransactionsOrderedByAmount(): Flow<List<TransactionEntity>>
        = transactionDao.getTransactionsOrderedByAmount()

    override fun getTransactionsOrderedByDate(): Flow<List<TransactionEntity>>
        = transactionDao.getTransactionsOrderedByDate()

    override fun getSumAmountExpenseByCategoryInPeriod(category: String, startDate: String, endDate: String): Flow<Double>
        = transactionDao.getSumAmountExpenseByCategoryInPeriod(category, startDate, endDate)

    override fun getTransactionsInPeriod(startDate: String, endDate: String): Flow<List<TransactionEntity>>
        = transactionDao.getTransactionsInPeriod(startDate, endDate)

    override fun getExpenseTotalAmount(): Flow<Double>
        = transactionDao.getTotalAmountExpense()

    override fun getIncomeTotalAmount(): Flow<Double>
        = transactionDao.getTotalAmountIncome()

    override fun getExpenseTransactions(): Flow<List<TransactionEntity>>
        = transactionDao.getExpenseTransactions()

    override fun getIncomeTransactions(): Flow<List<TransactionEntity>>
        = transactionDao.getIncomeTransactions()

    override fun getTransactionsByTypeAndPeriod(
        type: String,
        startDate: String,
        endDate: String
    ): Flow<List<TransactionEntity>>
        = transactionDao.getTransactionsByTypeAndPeriod(type, startDate, endDate)

    override suspend fun insert(transactionEntity: TransactionEntity)
            = transactionDao.insert(transactionEntity)

    override suspend fun update(transactionEntity: TransactionEntity)
            = transactionDao.update(transactionEntity)

    override suspend fun deleteOne(transactionEntity: TransactionEntity)
            = transactionDao.deleteOne(transactionEntity)
}