package com.example.expense_tracker.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.expense_tracker.utils.DateUtils
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
enum class TimeRange(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
) {
    Month(
        startTime = LocalDateTime.parse(DateUtils.getMonthRange().start),
        endTime = LocalDateTime.parse(DateUtils.getMonthRange().end),
    );
}