package com.example.finallab.domain.use_case.home

import com.example.finallab.domain.use_case.add_edit_transaction.GetTransactionsByQuantity

data class HomeUseCases(
    val getIncomeTotalAmount: GetIncomeTotalAmount,
    val getExpenseTotalAmount: GetExpenseTotalAmount,
    val getTransactionsByQuantity: GetTransactionsByQuantity
)
