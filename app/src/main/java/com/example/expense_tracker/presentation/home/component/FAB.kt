package com.example.expense_tracker.presentation.home.component

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.ui.theme.ReplacementTheme

@Composable
fun FAB(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = ReplacementTheme.colorScheme.primary,
        contentColor = ReplacementTheme.colorScheme.onPrimary,
        modifier = Modifier
            .offset(y= 45.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}