package com.example.expense_tracker.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.expense_tracker.domain.model.Range
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoField

@RequiresApi(Build.VERSION_CODES.O)
object DateUtils {
    private lateinit var _time: LocalDateTime
    fun getWeekRange() = Range(start = getStartOfTheWeek(), end = getEndOfTheWeek())
    fun getMonthRange()= Range(start = getStartOfTheMonth(), end = getEndOfTheMonth())
    fun getYearRange() = Range(start = getStartOfTheYear(), end = getEndOfTheYear())

    private fun getCurrentTime(start: Boolean): LocalDateTime {
        if (start) {
            return LocalDateTime.of(LocalDate.now(), LocalTime.MIN)
        }

        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX)
    }

    private fun getStartOfTheWeek(): String {
        _time = getCurrentTime(start = true)
        val startOfWeek = _time.with(ChronoField.DAY_OF_WEEK, 1)
        return startOfWeek.toString()
    }

    private fun getEndOfTheWeek(): String {
        _time = getCurrentTime(start = false)
        val endOfWeek = _time.with(ChronoField.DAY_OF_WEEK, 7)
        return endOfWeek.toString()
    }

    private fun getStartOfTheMonth(): String {
        _time = getCurrentTime(start = true)
        val startOfMonth = _time.with(ChronoField.DAY_OF_MONTH, 1)
        return startOfMonth.toString()
    }

    private fun getEndOfTheMonth(): String {
        _time = getCurrentTime(start = false)
        val endOfMonth = _time.withDayOfMonth(_time.month.length(_time.toLocalDate().isLeapYear))
        return endOfMonth.toString()
    }

    private fun getStartOfTheYear(): String {
        _time = getCurrentTime(start = true)
        val startOfYear = _time.withDayOfYear(1)
        return startOfYear.toString()
    }

    private fun getEndOfTheYear(): String {
        _time = getCurrentTime(start = false)
        val endOfYear = _time.withDayOfYear(_time.toLocalDate().lengthOfYear())
        return endOfYear.toString()
    }
}