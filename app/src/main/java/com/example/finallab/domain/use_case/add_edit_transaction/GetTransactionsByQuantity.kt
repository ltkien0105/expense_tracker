package com.example.finallab.domain.use_case.add_edit_transaction

import com.example.finallab.domain.repository.TransactionRepository

class GetTransactionsByQuantity(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(quantity: Int) = transactionRepository.getTransactionsByQuantity(quantity)
}