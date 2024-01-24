package com.example.finallab.presentation.add_edit_budget

import com.example.finallab.domain.model.DropdownItem

interface AddEditBudgetEvent {
    data class GetBudgetById(val id: Int): AddEditBudgetEvent
    data class SetAmount(val amount: String): AddEditBudgetEvent
    data class SetCategory(val category: DropdownItem): AddEditBudgetEvent
    data class SetRange(val range: DropdownItem): AddEditBudgetEvent
    data class SetCategoryDropdownExpanded(val isExpanded: Boolean): AddEditBudgetEvent
    data class SetRangeDropdownExpanded(val isExpanded: Boolean): AddEditBudgetEvent
    data object SaveBudget: AddEditBudgetEvent
}