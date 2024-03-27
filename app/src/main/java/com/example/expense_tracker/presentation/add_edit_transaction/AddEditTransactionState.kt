package com.example.expense_tracker.presentation.add_edit_transaction

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.expense_tracker.domain.model.DropdownItem
import com.example.expense_tracker.data.local.transaction.TransactionType
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class AddEditTransactionState (
    val id: Int = 0,
    val name: String = "",
    val type: DropdownItem = DropdownItem(null, TransactionType.Income.name),
    val category: DropdownItem = DropdownItem(null, "Select categories"),
    val amount: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val isTypeDropdownExpanded: Boolean = false,
    val isCategoryDropdownExpanded: Boolean = false,
    val isDatePickerShowed: Boolean = false
)
