package com.example.expense_tracker.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker.domain.use_case.home.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
): ViewModel() {
    private var _transactionHistoryState = MutableStateFlow(HomeState())
    val transactionHistoryState = _transactionHistoryState.asStateFlow()

    init {
        viewModelScope.launch {
            homeUseCases.getExpenseTotalAmount().collect {
                expense ->
                _transactionHistoryState.update {
                    it.copy(expense = expense)
                }
            }
        }

        viewModelScope.launch {
            homeUseCases.getIncomeTotalAmount().collect {
                    income ->
                _transactionHistoryState.update {
                    it.copy(income = income)
                }
            }
        }

        viewModelScope.launch {
            homeUseCases.getTransactionsByQuantity(10).collect {
                    transactionList ->
                _transactionHistoryState.update {
                    it.copy(transactions = transactionList)
                }
            }
        }
    }

}