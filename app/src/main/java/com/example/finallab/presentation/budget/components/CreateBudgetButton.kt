package com.example.finallab.presentation.budget.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.finallab.R
import com.example.finallab.ui.theme.DarkGreen
import com.example.finallab.ui.theme.ReplacementTheme

@Composable
fun CreateBudgetButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkGreen
        )
    ) {
        Text(
            stringResource(R.string.create_a_budget),
            style = ReplacementTheme.typography.labelMedium
        )
    }
}