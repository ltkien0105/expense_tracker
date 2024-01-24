package com.example.finallab.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finallab.R
import com.example.finallab.presentation.navgraph.Route
import com.example.finallab.ui.theme.AppBarColor
import com.example.finallab.ui.theme.ReplacementTheme
import com.example.finallab.ui.theme.WhiteAlpha6

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredAppBar(
    currentScreen: String?
) {
    val title = when (currentScreen) {
        Route.BottomNavigationScreen.Statistic.route -> stringResource(R.string.statistic)
        Route.BottomNavigationScreen.Budget.route -> stringResource(R.string.budget)
        Route.AddEditTransactionScreen.route -> stringResource(R.string.add_edit_transaction)
        else -> stringResource(R.string.settings)
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                style = ReplacementTheme.typography.titleMedium
            )
        },
        actions = {
            when (currentScreen) {
                Route.BottomNavigationScreen.Statistic.route ->
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(end = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_download),
                            contentDescription = stringResource(R.string.download),
                            tint = Color.Black,
                        )
                    }
                else ->
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .background(
                                color = WhiteAlpha6,
                                shape = RoundedCornerShape(7.dp)
                            )
                            .size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = stringResource(id = R.string.notification),
                            tint = Color.White,
                        )
                    }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (currentScreen == Route.BottomNavigationScreen.Statistic.route) Color.White else AppBarColor,
            titleContentColor = if (currentScreen == Route.BottomNavigationScreen.Statistic.route) Color.Black else Color.White
        ),
    )
}

@Preview
@Composable
fun CenteredAppBarPreview() {
    CenteredAppBar(Route.BottomNavigationScreen.Statistic.route)
}