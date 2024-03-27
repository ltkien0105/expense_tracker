package com.example.expense_tracker.presentation.budget.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

fun DrawScope.drawBudgetArc(
    sweepAngle: Float,
    color: Color
) {
    drawArc(
        color = color,
        startAngle = -180f,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = size,
        style = Stroke(
            width = 10.dp.toPx(),
            cap = StrokeCap.Round
        )
    )
}