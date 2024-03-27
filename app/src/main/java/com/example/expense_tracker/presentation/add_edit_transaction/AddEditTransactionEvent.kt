package com.example.expense_tracker.presentation.add_edit_transaction

import android.content.Context
import com.example.expense_tracker.domain.model.DropdownItem
import java.time.LocalDateTime

sealed interface AddEditTransactionEvent {
    data class SetName(val name: String): AddEditTransactionEvent
    data class SetType(val type: DropdownItem): AddEditTransactionEvent
    data class SetCategory(val category: DropdownItem): AddEditTransactionEvent
    data class SetAmount(val amount: String): AddEditTransactionEvent
    data class SetDate(val date: LocalDateTime): AddEditTransactionEvent
    data class SetTypeDropdownExpanded(val isExpanded: Boolean): AddEditTransactionEvent
    data class SetCategoryDropdownExpanded(val isExpanded: Boolean): AddEditTransactionEvent
    data class SetDatePickerShowed(val isShowed: Boolean): AddEditTransactionEvent
    data object ClearAmount: AddEditTransactionEvent
    data class SaveTransaction(val context: Context): AddEditTransactionEvent
    data class GetTransactionById(val id: Int, val context: Context): AddEditTransactionEvent
}