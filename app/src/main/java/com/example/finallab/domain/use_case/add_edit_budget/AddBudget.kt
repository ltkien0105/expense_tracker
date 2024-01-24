package com.example.finallab.domain.use_case.add_edit_budget

import com.example.finallab.data.local.budget.BudgetEntity
import com.example.finallab.domain.repository.BudgetRepository

class AddBudget(
    private val budgetRepository: BudgetRepository
) {
    suspend operator fun invoke(budget: BudgetEntity) = budgetRepository.insert(budget)
}