package com.example.expense_tracker.di

import android.app.Application
import androidx.room.Room
import com.example.expense_tracker.data.local.transaction.TransactionDao
import com.example.expense_tracker.data.local.TransactionDatabase
import com.example.expense_tracker.data.local.budget.BudgetDao
import com.example.expense_tracker.domain.repository.BudgetRepository
import com.example.expense_tracker.domain.repository.TransactionRepository
import com.example.expense_tracker.domain.use_case.add_edit_budget.AddBudget
import com.example.expense_tracker.domain.use_case.add_edit_budget.AddEditBudgetUseCases
import com.example.expense_tracker.domain.use_case.add_edit_budget.EditBudget
import com.example.expense_tracker.domain.use_case.add_edit_budget.GetAllBudget
import com.example.expense_tracker.domain.use_case.add_edit_budget.GetBudgetById
import com.example.expense_tracker.domain.use_case.add_edit_transaction.AddTransaction
import com.example.expense_tracker.domain.use_case.add_edit_transaction.DeleteTransaction
import com.example.expense_tracker.domain.use_case.add_edit_transaction.EditTransaction
import com.example.expense_tracker.domain.use_case.add_edit_transaction.GetAllTransactions
import com.example.expense_tracker.domain.use_case.add_edit_transaction.GetExpenseTransactions
import com.example.expense_tracker.domain.use_case.add_edit_transaction.GetIncomeTransactions
import com.example.expense_tracker.domain.use_case.add_edit_transaction.GetTransactionById
import com.example.expense_tracker.domain.use_case.add_edit_transaction.GetTransactionsByQuantity
import com.example.expense_tracker.domain.use_case.add_edit_transaction.GetTransactionsByTypeAndPeriod
import com.example.expense_tracker.domain.use_case.add_edit_transaction.GetTransactionsOrderedByAmount
import com.example.expense_tracker.domain.use_case.add_edit_transaction.GetTransactionsOrderedByDate
import com.example.expense_tracker.domain.use_case.add_edit_transaction.AddEditTransactionUseCases
import com.example.expense_tracker.domain.use_case.budget.BudgetUseCases
import com.example.expense_tracker.domain.use_case.budget.GetBudgetAndListExpensesTransactionByMonth
import com.example.expense_tracker.domain.use_case.budget.GetTotalBudget
import com.example.expense_tracker.domain.use_case.home.GetExpenseTotalAmount
import com.example.expense_tracker.domain.use_case.home.GetIncomeTotalAmount
import com.example.expense_tracker.domain.use_case.home.HomeUseCases
import com.example.expense_tracker.domain.use_case.validation.ValidationAmount
import com.example.expense_tracker.domain.use_case.validation.ValidationEmail
import com.example.expense_tracker.domain.use_case.validation.ValidationUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTransactionDatabase(
        application: Application
    ): TransactionDatabase {
        return Room.databaseBuilder(
            application,
            TransactionDatabase::class.java,
            "transaction_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(
        transactionDatabase: TransactionDatabase
    ): TransactionDao = transactionDatabase.transactionDao

    @Provides
    @Singleton
    fun provideBudgetDao(
        transactionDatabase: TransactionDatabase
    ): BudgetDao = transactionDatabase.budgetDao

    @Provides
    @Singleton
    fun provideHomeUseCases(
        transactionRepository: TransactionRepository
    ): HomeUseCases {
        return HomeUseCases(
            getIncomeTotalAmount = GetIncomeTotalAmount(transactionRepository),
            getExpenseTotalAmount = GetExpenseTotalAmount(transactionRepository),
            getTransactionsByQuantity = GetTransactionsByQuantity(transactionRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAddEditTransactionUseCases(
        transactionRepository: TransactionRepository
    ): AddEditTransactionUseCases {
        return AddEditTransactionUseCases(
            getAll = GetAllTransactions(transactionRepository),
            getById = GetTransactionById(transactionRepository),
            getTransactionsByQuantity = GetTransactionsByQuantity(transactionRepository),
            getTransactionsOrderedByAmount = GetTransactionsOrderedByAmount(transactionRepository),
            getTransactionsOrderedByDate = GetTransactionsOrderedByDate(transactionRepository),
            getExpenseTransactions = GetExpenseTransactions(transactionRepository),
            getIncomeTransactions = GetIncomeTransactions(transactionRepository),
            getTransactionsByTypeAndPeriod = GetTransactionsByTypeAndPeriod(transactionRepository),
            insert = AddTransaction(transactionRepository),
            update = EditTransaction(transactionRepository),
            deleteOne = DeleteTransaction(transactionRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAddEditBudgetUseCases(
        budgetRepository: BudgetRepository
    ): AddEditBudgetUseCases {
        return AddEditBudgetUseCases(
            addBudget = AddBudget(budgetRepository),
            editBudget = EditBudget(budgetRepository),
            getAllBudget = GetAllBudget(budgetRepository),
            getBudgetById = GetBudgetById(budgetRepository)
        )
    }

    @Provides
    @Singleton
    fun provideBudgetUseCases(
        budgetRepository: BudgetRepository
    ): BudgetUseCases {
        return BudgetUseCases(
            getTotalBudget = GetTotalBudget(budgetRepository),
            getBudgetAndListExpensesTransactionByMonth = GetBudgetAndListExpensesTransactionByMonth(budgetRepository)
        )
    }

    @Provides
    @Singleton
    fun provideValidationUseCases(): ValidationUseCases {
        return ValidationUseCases(
            validationEmail = ValidationEmail(),
            validationAmount = ValidationAmount()
        )
    }
}