package com.example.expense_tracker.presentation.main.components

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
import com.example.expense_tracker.R
import com.example.expense_tracker.presentation.navgraph.Route
import com.example.expense_tracker.ui.theme.ReplacementTheme
import com.example.expense_tracker.ui.theme.WhiteAlpha6

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredAppBar(
    currentScreen: String?
) {
    val title = when (currentScreen) {
        Route.BottomNavigationScreen.Statistic.route -> stringResource(R.string.statistic)
        Route.BottomNavigationScreen.Budget.route -> stringResource(R.string.budget)
        Route.AddEditTransactionScreen.route -> stringResource(R.string.add_edit_transaction)
        Route.AddEditBudgetScreen.route -> stringResource(R.string.add_edit_budget)
        else -> stringResource(R.string.settings)
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                style = ReplacementTheme.typography.titleMedium,
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
                        )
                    }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ReplacementTheme.colorScheme.appBar,
            titleContentColor = ReplacementTheme.colorScheme.onAppBar,
            actionIconContentColor = ReplacementTheme.colorScheme.onAppBar,
        ),
    )
}

@Preview
@Composable
fun CenteredAppBarPreview() {
    CenteredAppBar(Route.BottomNavigationScreen.Statistic.route)
}