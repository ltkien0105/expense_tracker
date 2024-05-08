package com.example.expense_tracker.domain.use_case.budget

data class BudgetUseCases(
    val getTotalBudget: GetTotalBudget,
    val getBudgetAndListExpensesTransactionByMonth: GetBudgetAndListExpensesTransactionByMonth
)
