package com.example.expense_tracker.domain.use_case.home

import com.example.expense_tracker.domain.repository.TransactionRepository

class GetIncomeTotalAmount(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke() = transactionRepository.getIncomeTotalAmount()
}