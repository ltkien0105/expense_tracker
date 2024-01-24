package com.example.finallab.presentation.statistic.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.finallab.R
import com.example.finallab.presentation.statistic.StatisticState
import com.example.finallab.presentation.statistic.chart_custom.rememberMarker
import com.example.finallab.ui.theme.DarkGreen
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.layout.fullWidth
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun ChartComponent(
    statisticState: StatisticState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val chartEntryModel = if (statisticState.rangeSelected == 3) {
        val entryList = statisticState.chartData.chunked(7) {
            it.sum()
        }
        entryModelOf(*entryList.toTypedArray())
    } else {
        entryModelOf(*statisticState.chartData.toTypedArray())
    }

    val bottomAxis = AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
            value, _ ->
        when(statisticState.rangeSelected) {
            0 -> when(value) {
                0f -> context.getString(R.string.mon)
                1f -> context.getString(R.string.tue)
                2f -> context.getString(R.string.wed)
                3f -> context.getString(R.string.thu)
                4f -> context.getString(R.string.fri)
                5f -> context.getString(R.string.sat)
                else -> context.getString(R.string.sun)
            }
            else -> (value + 1).toInt().toString()
        }
    }
    val marker = rememberMarker(statisticState.rangeSelected == 1)

    ProvideChartStyle {
        Chart(
            chart = lineChart(
                spacing = if (statisticState.rangeSelected == 1) 10f.dp else 5f.dp,
                lines = listOf(
                    LineChart.LineSpec(
                        lineColor = 0xFF438883.toInt(),
                        lineBackgroundShader = DynamicShaders.fromBrush(
                            Brush.verticalGradient(
                                listOf(
                                    DarkGreen.copy(
                                        alpha = 0.5f
                                    ),
                                    Color.Transparent
                                )
                            )
                        )
                    )
                )
            ),
            model = chartEntryModel,
            startAxis = rememberStartAxis(
                guideline = null,
                tickLength = 0.dp
            ),
            bottomAxis = rememberBottomAxis(
                guideline = null,
                valueFormatter = bottomAxis,
                tickLength = 0.dp,
                itemPlacer = AxisItemPlacer.Horizontal.default(
                    offset = 20
                )
            ),
            marker = marker,
            modifier = modifier,
            getXStep = {
                if (it.entries[0].isEmpty() || statisticState.rangeSelected != 1)
                    1f
                else 7f
            },
            horizontalLayout = HorizontalLayout.fullWidth(
                scalableEndPadding = 2.dp,
                scalableStartPadding = 2.dp
            )
        )
    }
}