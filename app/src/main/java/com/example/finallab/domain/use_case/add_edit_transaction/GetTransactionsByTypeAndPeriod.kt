package com.example.finallab.domain.use_case.add_edit_transaction

import com.example.finallab.domain.model.UiText
import com.example.finallab.domain.repository.TransactionRepository

class GetTransactionsByTypeAndPeriod (
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(
        type: String,
        startDate: String,
        endDate: String
    ) = transactionRepository.getTransactionsByTypeAndPeriod(
        type,
        startDate,
        endDate
    )
}