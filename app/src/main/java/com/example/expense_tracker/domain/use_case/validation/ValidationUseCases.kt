package com.example.expense_tracker.domain.use_case.validation

data class ValidationUseCases(
    val validationEmail: ValidationEmail,
    val validationAmount: ValidationAmount
)
