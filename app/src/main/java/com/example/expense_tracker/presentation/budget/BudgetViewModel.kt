package com.example.expense_tracker.presentation.budget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker.domain.model.BudgetItem
import com.example.expense_tracker.domain.use_case.budget.BudgetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val budgetUseCases: BudgetUseCases
) : ViewModel() {
    private val _budgetState = MutableStateFlow(BudgetState())
    val budgetState = _budgetState.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                budgetUseCases.getTotalBudget().collect { totalBudget ->
                    _budgetState.update {
                        it.copy(
                            totalBudget = totalBudget
                        )
                    }
                }
            }

            launch {
                budgetUseCases.getBudgetAndListExpensesTransactionByMonth()
                    .collect { budgetAndListExpensesTransaction ->

                        var totalSpent = 0.0
                        val budgetList =
                            budgetAndListExpensesTransaction.map { budgetWithTransactions ->
                                val budgetItem = budgetWithTransactions.budget
                                val totalSpentEachCategory =
                                    budgetWithTransactions.transactions.sumOf { it.amount.toDouble() }
                                totalSpent += totalSpentEachCategory

                                BudgetItem(
                                    id = budgetItem.id,
                                    category = budgetItem.category,
                                    totalAmount = budgetItem.totalAmount,
                                    leftAmount = budgetItem.totalAmount - totalSpentEachCategory,
                                    startTime = budgetItem.startTime,
                                    endTime = budgetItem.endTime
                                )
                            }

                        if (budgetList.isNotEmpty()) {
                            val today = LocalDateTime.now().dayOfYear

                            val dayRemaining = budgetList[0].endTime.dayOfYear - today
                            _budgetState.update {
                                it.copy(
                                    budgetList = budgetList,
                                    totalSpent = totalSpent,
                                    dayRemaining = dayRemaining
                                )
                            }
                        }
                    }
            }
        }
    }
}
