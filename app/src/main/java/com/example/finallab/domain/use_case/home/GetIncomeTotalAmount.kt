package com.example.finallab.domain.use_case.home

import com.example.finallab.domain.repository.TransactionRepository

class GetIncomeTotalAmount(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke() = transactionRepository.getIncomeTotalAmount()
}