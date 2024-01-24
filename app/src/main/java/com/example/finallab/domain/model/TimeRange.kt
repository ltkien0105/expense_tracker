package com.example.finallab.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.finallab.utils.DateUtils
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