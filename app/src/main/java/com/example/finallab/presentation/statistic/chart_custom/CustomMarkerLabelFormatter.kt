package com.example.finallab.presentation.statistic.chart_custom

import android.text.Spannable
import android.text.style.ForegroundColorSpan
import com.patrykandpatrick.vico.core.chart.values.ChartValues
import com.patrykandpatrick.vico.core.extension.appendCompat
import com.patrykandpatrick.vico.core.extension.sumOf
import com.patrykandpatrick.vico.core.extension.transformToSpannable
import com.patrykandpatrick.vico.core.marker.Marker
import com.patrykandpatrick.vico.core.marker.MarkerLabelFormatter

class CustomMarkerLabelFormatter(
    private val colorCode: Boolean = true,
    var isMonth: Boolean = false,
) : MarkerLabelFormatter {
    override fun getLabel(
        markedEntries: List<Marker.EntryModel>,
        chartValues: ChartValues,
    ): CharSequence = markedEntries.transformToSpannable(
        prefix =
            if (markedEntries.size > 1)
                PATTERN.format(markedEntries.sumOf { it.entry.y }) + " ("
            else "",
        postfix = if (markedEntries.size > 1) ")" else "",
        separator = "; ",
    ) { model ->
        if (colorCode) {
            appendCompat(
                if (isMonth)
                    PATTERN_MONTH.format(model.entry.x + 1, model.entry.y)
                else
                    PATTERN.format(model.entry.y),
                ForegroundColorSpan(model.color),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
            )
        } else {
            append(PATTERN.format(model.entry.y))
        }
    }

    private companion object {
        const val PATTERN = "%.02f"
        const val PATTERN_MONTH = "Day %.0f: %.02f"
    }
}