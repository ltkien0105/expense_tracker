package com.example.expense_tracker.domain.use_case.add_edit_budget

import com.example.expense_tracker.data.local.budget.BudgetEntity
import com.example.expense_tracker.domain.repository.BudgetRepository

class AddBudget(
    private val budgetRepository: BudgetRepository
) {
    suspend operator fun invoke(budget: BudgetEntity) = budgetRepository.insert(budget)
}