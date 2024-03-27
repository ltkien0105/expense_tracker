package com.example.expense_tracker.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.R
import com.example.expense_tracker.ui.theme.AppBarColor
import com.example.expense_tracker.ui.theme.ReplacementTheme
import com.example.expense_tracker.ui.theme.WhiteAlpha6

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarHome() {
    TopAppBar(
        title = {
            Column {
                Text(
                    stringResource(R.string.good_afternoon),
                    style = ReplacementTheme.typography.titleSmall
                )
                Text(
                    stringResource(R.string.name_of_user, "Trung Kien"),
                    style = ReplacementTheme.typography.titleLarge
                )
            }

        },
        actions = {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .background(
                        color = WhiteAlpha6,
                        shape = RoundedCornerShape(7.dp)
                    )
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = stringResource(R.string.notification),
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppBarColor,
            titleContentColor = Color.White
        ),
    )
}

@Preview
@Composable
fun AppBarPreview() {
    AppBarHome()
}