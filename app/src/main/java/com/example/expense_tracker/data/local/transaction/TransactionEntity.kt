package com.example.expense_tracker.data.local.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

enum class TransactionType {
    Income,
    Expense
}

@Entity(tableName = "transaction_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "icon_name")
    val iconName: String,
    val title: String,
    val date: LocalDateTime,
    val category: String,
    val type: TransactionType,
    val amount: Float
)
