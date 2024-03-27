package com.example.expense_tracker.presentation.add_edit_budget

import com.example.expense_tracker.domain.model.DropdownItem

interface AddEditBudgetEvent {
    data class GetBudgetById(val id: Int): AddEditBudgetEvent
    data class SetAmount(val amount: String): AddEditBudgetEvent
    data class SetCategory(val category: DropdownItem): AddEditBudgetEvent
    data class SetRange(val range: DropdownItem): AddEditBudgetEvent
    data class SetCategoryDropdownExpanded(val isExpanded: Boolean): AddEditBudgetEvent
    data class SetRangeDropdownExpanded(val isExpanded: Boolean): AddEditBudgetEvent
    data object SaveBudget: AddEditBudgetEvent
}