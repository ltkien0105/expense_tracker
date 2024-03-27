package com.example.expense_tracker.domain.use_case.validation

class ValidationAmount {
    operator fun invoke(amount: String): ValidationResult {
        if (amount.isBlank())
            return ValidationResult(
                isSuccess = false,
                errorMessage = "This field is required"
            )
        if (amount.toFloatOrNull() == null)
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Amount must be a number"
            )

        return ValidationResult(
            isSuccess = true
        )
    }
}