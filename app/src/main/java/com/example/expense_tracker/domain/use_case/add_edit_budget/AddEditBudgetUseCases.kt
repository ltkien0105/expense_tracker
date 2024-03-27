package com.example.expense_tracker.domain.use_case.add_edit_budget

data class AddEditBudgetUseCases(
    val addBudget: AddBudget,
    val editBudget: EditBudget,
    val getAllBudget: GetAllBudget,
    val getBudgetById: GetBudgetById,
)
