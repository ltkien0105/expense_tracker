package com.example.finallab.domain.use_case.validation

import android.util.Patterns

class ValidationEmail {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank())
            return ValidationResult(
                isSuccess = false,
                errorMessage = "This field is required"
            )
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return ValidationResult(
                isSuccess = false,
                errorMessage = "Email is not valid"
            )

        return ValidationResult(
            isSuccess = true
        )
    }
}