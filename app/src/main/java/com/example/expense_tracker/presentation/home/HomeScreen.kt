package com.example.expense_tracker.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.expense_tracker.R
import com.example.expense_tracker.presentation.common.BackgroundScreen
import com.example.expense_tracker.presentation.common.TransactionItem
import com.example.expense_tracker.presentation.home.component.CardInformation
import com.example.expense_tracker.presentation.navgraph.Route
import com.example.expense_tracker.ui.theme.Gray
import com.example.expense_tracker.ui.theme.ReplacementTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val transactionHistoryState by homeViewModel.transactionHistoryState.collectAsState()

    BackgroundScreen {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            CardInformation(
                transactionHistoryState.income - transactionHistoryState.expense,
                transactionHistoryState.income,
                transactionHistoryState.expense,
                modifier = Modifier
                    .padding(top = 64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            //Transaction History section
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.transaction_history),
                        style = ReplacementTheme.typography.titleMedium.copy(
                            color = ReplacementTheme.colorScheme.onBackground
                        )
                    )
                    TextButton(onClick = {
                        navHostController.navigate(Route.BottomNavigationScreen.Statistic.route)
                    }) {
                        Text(
                            stringResource(id = R.string.see_all),
                            style = ReplacementTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = ReplacementTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                            )
                        )
                    }
                }
            }
            LazyColumn {
                items( transactionHistoryState.transactions) {
                    TransactionItem(transactionEntity = it)
                }
            }
        }
    }
}