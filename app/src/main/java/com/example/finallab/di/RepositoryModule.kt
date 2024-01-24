package com.example.finallab.di

import com.example.finallab.data.repository.OfflineBudgetRepositoryImpl
import com.example.finallab.data.repository.OfflineTransactionRepositoryImpl
import com.example.finallab.domain.repository.BudgetRepository
import com.example.finallab.domain.repository.TransactionRepository
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