package com.example.expense_tracker.presentation.statistic

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker.data.local.transaction.TransactionEntity
import com.example.expense_tracker.data.local.transaction.TransactionType
import com.example.expense_tracker.domain.use_case.add_edit_transaction.AddEditTransactionUseCases
import com.example.expense_tracker.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val  addEditTransactionUseCases: AddEditTransactionUseCases
): ViewModel() {
    private val _statisticState = MutableStateFlow(StatisticState())
    val statisticState = _statisticState.asStateFlow()

    private var _prevState = StatisticState(tabSelected = -1, rangeSelected = -1)

    private fun getWeekChart(transactions: List<TransactionEntity>): List<Float> {
        val daysOfWeek = MutableList(7) { 0f }
        transactions.forEach {
            daysOfWeek[it.date.dayOfWeek.value - 1] += it.amount
        }

        return daysOfWeek
    }

    private fun getMonthChart(transactions: List<TransactionEntity>): List<Float> {
        val lengthMonth = LocalDate.now().lengthOfMonth()
        val daysOfMonth = MutableList(lengthMonth) { 0f }
        transactions.forEach {
            daysOfMonth[it.date.dayOfMonth - 1] += it.amount
        }

        return daysOfMonth
    }

    private fun getYearChart(transactions: List<TransactionEntity>): List<Float> {
        val daysOfYear = MutableList(12) { 0f }
        transactions.forEach {
            daysOfYear[it.date.month.value - 1] += it.amount
        }

        return daysOfYear
    }

    init {
        viewModelScope.launch {
            _statisticState
                .filter {
                    it.tabSelected != _prevState.tabSelected
                    || it.rangeSelected != _prevState.rangeSelected
                }
                .flatMapLatest {
                    _prevState = it
                    val dateRange = when(it.rangeSelected) {
                        0 -> DateUtils.getWeekRange()
                        1 -> DateUtils.getMonthRange()
                        else -> DateUtils.getYearRange()
                    }

                    addEditTransactionUseCases.getTransactionsByTypeAndPeriod(
                        type = if (it.tabSelected == 0) TransactionType.Expense.name else TransactionType.Income.name,
                        startDate = dateRange.start,
                        endDate = dateRange.end
                    )
                }.collectLatest {
                    transactionList ->
                    _statisticState.update {
                        it.copy(
                            transactions = transactionList,
                            chartData = when(it.rangeSelected) {
                                0 -> getWeekChart(transactionList)
                                1 -> getMonthChart(transactionList)
                                else -> getYearChart(transactionList)
                            }
                        )
                    }
                }
        }
    }

    fun onEvent(event: StatisticEvent) {
        when(event) {
            is StatisticEvent.ChangeTab -> {
                _statisticState.update {
                    it.copy(
                        tabSelected = event.tabIndex
                    )
                }
            }
            is StatisticEvent.ChangeRange -> {
                _statisticState.update {
                    it.copy(
                        rangeSelected = event.rangeIndex
                    )
                }
            }
            StatisticEvent.SortTransactions -> {
                _statisticState.update {
                    it.copy(
                        transactions = it.transactions.reversed()
                    )
                }
            }
        }
    }
}