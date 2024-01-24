package com.example.finallab.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.finallab.data.local.budget.BudgetDao
import com.example.finallab.data.local.budget.BudgetEntity
import com.example.finallab.data.local.transaction.TransactionDao
import com.example.finallab.data.local.transaction.TransactionEntity

@Database(entities = [TransactionEntity::class, BudgetEntity::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TransactionDatabase: RoomDatabase() {
    abstract val transactionDao: TransactionDao

    abstract val budgetDao: BudgetDao
}