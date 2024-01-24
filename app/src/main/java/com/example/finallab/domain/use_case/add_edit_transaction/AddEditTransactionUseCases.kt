package com.example.finallab.domain.use_case.add_edit_transaction

data class AddEditTransactionUseCases(
    val getAll: GetAllTransactions,
    val getById: GetTransactionById,
    val getTransactionsByQuantity: GetTransactionsByQuantity,
    val getTransactionsOrderedByAmount: GetTransactionsOrderedByAmount,
    val getTransactionsOrderedByDate: GetTransactionsOrderedByDate,
    val getExpenseTransactions: GetExpenseTransactions,
    val getIncomeTransactions: GetIncomeTransactions,
    val getTransactionsByTypeAndPeriod: GetTransactionsByTypeAndPeriod,
    val insert: AddTransaction,
    val update: EditTransaction,
    val deleteOne: DeleteTransaction
)
