package com.example.finallab.domain.use_case.add_edit_budget

import com.example.finallab.domain.repository.BudgetRepository

class GetAllBudget(
    private val budgetRepository: BudgetRepository
) {
    operator fun invoke() = budgetRepository.getAllBudgets()
}