package com.example.expense_tracker.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.expense_tracker.R
import com.example.expense_tracker.ui.theme.ReplacementTheme

@Composable
fun BackgroundScreen(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ReplacementTheme.colorScheme.background),
        content = {
            Image(
                painter = painterResource(id = R.drawable.background_rectangle_curved),
                contentDescription = null,
            )
            content()
        }
    )

}