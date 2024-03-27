package com.example.expense_tracker.presentation.add_edit_budget

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.expense_tracker.data.local.budget.BudgetEntity
import com.example.expense_tracker.domain.model.DropdownItem
import com.example.expense_tracker.domain.model.TimeRange
import com.example.expense_tracker.utils.getTimeRangeString

@RequiresApi(Build.VERSION_CODES.O)
data class AddEditBudgetState  (
    val id: Int = 0,
    val amount: String = "",
    val category: DropdownItem = DropdownItem(
        null,
        "Select category"
    ),
    val range: DropdownItem = DropdownItem(
        null,
        TimeRange.Month.getTimeRangeString(),
    ),
    val isCategoryDropdownExpand: Boolean = false,
    val isRangeDropdownExpand: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
fun AddEditBudgetState.toBudgetEntity(): BudgetEntity {
    return BudgetEntity(
        id = this.id,
        totalAmount = this.amount.toDouble(),
        category = this.category.label,
        startTime = TimeRange.Month.startTime,
        endTime = TimeRange.Month.endTime
    )
}
