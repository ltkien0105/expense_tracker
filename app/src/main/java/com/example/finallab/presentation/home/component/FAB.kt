package com.example.finallab.presentation.home.component

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.finallab.ui.theme.DarkGreen

@Composable
fun FAB(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = DarkGreen,
        contentColor = Color.White,
        modifier = Modifier
            .offset(y= 45.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}