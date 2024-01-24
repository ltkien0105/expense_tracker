package com.example.finallab.domain.use_case.budget

data class BudgetUseCases(
    val getTotalBudget: GetTotalBudget,
    val getBudgetAndListExpensesTransaction: GetBudgetAndListExpensesTransaction
)
