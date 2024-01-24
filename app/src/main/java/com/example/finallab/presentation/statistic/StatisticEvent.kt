package com.example.finallab.presentation.statistic

sealed class StatisticEvent {
    data class ChangeTab(val tabIndex: Int) : StatisticEvent()
    data class ChangeRange(val rangeIndex: Int) : StatisticEvent()
    data object SortTransactions: StatisticEvent()
}