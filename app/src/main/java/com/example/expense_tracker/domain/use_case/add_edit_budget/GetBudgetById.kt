package com.example.expense_tracker.domain.use_case.add_edit_budget

import com.example.expense_tracker.domain.repository.BudgetRepository

class GetBudgetById(
    private val budgetRepository: BudgetRepository
) {
    operator fun invoke(id: Int) = budgetRepository.getBudgetById(id)
}