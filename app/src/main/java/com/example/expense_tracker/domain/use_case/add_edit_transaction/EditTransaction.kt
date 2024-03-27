package com.example.expense_tracker.domain.use_case.add_edit_transaction

import com.example.expense_tracker.data.local.transaction.TransactionEntity
import com.example.expense_tracker.domain.repository.TransactionRepository

class EditTransaction(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(transaction: TransactionEntity) = transactionRepository.update(transaction)
}