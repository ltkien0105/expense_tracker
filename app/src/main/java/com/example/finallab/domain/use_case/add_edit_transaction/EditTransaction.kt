package com.example.finallab.domain.use_case.add_edit_transaction

import com.example.finallab.data.local.transaction.TransactionEntity
import com.example.finallab.domain.repository.TransactionRepository

class EditTransaction(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(transaction: TransactionEntity) = transactionRepository.update(transaction)
}