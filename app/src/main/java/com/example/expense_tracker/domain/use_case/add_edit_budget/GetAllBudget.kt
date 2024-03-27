package com.example.expense_tracker.domain.use_case.add_edit_budget

import com.example.expense_tracker.domain.repository.BudgetRepository

class GetAllBudget(
    private val budgetRepository: BudgetRepository
) {
    operator fun invoke() = budgetRepository.getAllBudgets()
}