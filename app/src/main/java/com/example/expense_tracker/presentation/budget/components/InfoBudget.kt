package com.example.expense_tracker.presentation.budget.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.ui.theme.ReplacementTheme

@Composable
fun InfoBudget(
    amount: Number,
    title: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            amount.toString(),
            textAlign = TextAlign.Center,
            style = ReplacementTheme.typography.bodyLarge
        )
        Text(
            title,
            textAlign = TextAlign.Center,
            style = ReplacementTheme.typography.labelSmall
        )
    }
}