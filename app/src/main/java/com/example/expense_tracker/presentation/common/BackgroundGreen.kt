package com.example.expense_tracker.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.expense_tracker.R

@Composable
fun BackgroundScreen(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            Image(
                painter = painterResource(id = R.drawable.background_rectangle_curved),
                contentDescription = null,
            )
            content()
        }
    )

}