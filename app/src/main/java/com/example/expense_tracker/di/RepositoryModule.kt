package com.example.expense_tracker.di

import com.example.expense_tracker.data.repository.OfflineBudgetRepositoryImpl
import com.example.expense_tracker.data.repository.OfflineTransactionRepositoryImpl
import com.example.expense_tracker.domain.repository.BudgetRepository
import com.example.expense_tracker.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOfflineTransactionRepository(
        offlineTransactionRepositoryImpl: OfflineTransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    @Singleton
    abstract fun bindOfflineBudgetRepository(
        offlineBudgetRepositoryImpl: OfflineBudgetRepositoryImpl
    ): BudgetRepository
}