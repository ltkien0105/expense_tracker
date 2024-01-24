package com.example.finallab.domain.use_case.budget

import com.example.finallab.domain.repository.BudgetRepository

class GetTotalBudget(
    private val budgetRepository: BudgetRepository
) {
    operator fun invoke() = budgetRepository.getTotalBudget()
}