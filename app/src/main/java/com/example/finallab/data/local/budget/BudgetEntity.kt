package com.example.finallab.data.local.budget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finallab.domain.model.BudgetItem
import com.example.finallab.domain.model.CategoriesItem
import com.example.finallab.domain.model.DropdownItem
import com.example.finallab.domain.model.TimeRange
import com.example.finallab.presentation.add_edit_budget.AddEditBudgetState
import com.example.finallab.utils.getTimeRangeString
import java.time.LocalDateTime
import kotlin.enums.enumEntries

@Entity(tableName = "budget_table")
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val category: String,
    @ColumnInfo(name = "total_amount")
    val totalAmount: Double,
    @ColumnInfo(name = "start_time")
    val startTime: LocalDateTime,
    @ColumnInfo(name = "end_time")
    val endTime: LocalDateTime
)

fun BudgetEntity.toBudgetItem(): BudgetItem {
    return BudgetItem(
        id = this.id,
        category = this.category,
        totalAmount = this.totalAmount,
        leftAmount = totalAmount,
        startTime = this.startTime,
        endTime = this.endTime
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun BudgetEntity.toAddEditBudgetState(): AddEditBudgetState {
    return AddEditBudgetState(
        id = this.id,
        amount = this.totalAmount.toString(),
        category = DropdownItem(
            enumValueOf<CategoriesItem>(category).icon,
            this.category
        ),
        range = DropdownItem(
            null,
            TimeRange.Month.getTimeRangeString()
        )
    )
}