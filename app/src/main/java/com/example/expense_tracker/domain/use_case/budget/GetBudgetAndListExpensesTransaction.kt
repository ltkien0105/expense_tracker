package com.example.expense_tracker.domain.use_case.budget

import com.example.expense_tracker.domain.repository.BudgetRepository

class GetBudgetAndListExpensesTransaction(
    private val budgetRepository: BudgetRepository
) {
    operator fun invoke() = budgetRepository.getBudgetAndListExpensesTransaction()
}