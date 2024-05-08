package com.example.expense_tracker.presentation.statistic

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.R
import com.example.expense_tracker.presentation.common.TransactionItem
import com.example.expense_tracker.presentation.navgraph.Route
import com.example.expense_tracker.presentation.statistic.component.ChartComponent
import com.example.expense_tracker.ui.theme.Gray
import com.example.expense_tracker.ui.theme.ReplacementTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticScreen(
    navHostController: NavHostController,
    statisticViewModel: StatisticViewModel = hiltViewModel()
) {
    val ranges = listOf(
        stringResource(R.string.week),
        stringResource(R.string.month),
        stringResource(R.string.year),
    )
    val tabs = listOf(
        stringResource(R.string.expense),
        stringResource(id = R.string.income),
    )

    val statisticState by statisticViewModel.statisticState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ReplacementTheme.colorScheme.background)
    ) {
        SecondaryTabRow(
            selectedTabIndex = statisticState.tabSelected,
            containerColor = ReplacementTheme.colorScheme.background,
            contentColor = ReplacementTheme.colorScheme.onBackground,
            indicator = @Composable {
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(
                        statisticState.tabSelected,
                        matchContentSize = false
                    ),
                    color = Color(0xFF8b75bd)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = statisticState.tabSelected == index,
                    onClick = {
                        statisticViewModel.onEvent(
                            StatisticEvent.ChangeTab(index)
                        )
                    },
                ) {
                    Text(
                        text = title,
                        style = ReplacementTheme.typography.titleMedium.copy(
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                ranges.forEachIndexed { index, range ->
                    Button(
                        onClick = {
                            statisticViewModel.onEvent(
                                StatisticEvent.ChangeRange(index)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                            if (statisticState.rangeSelected == index) ReplacementTheme.colorScheme.primary else Color.White,
                            contentColor =
                            if (statisticState.rangeSelected == index) Color.White else Gray
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = range,
                            style = ReplacementTheme.typography.labelSmall.copy(
                                fontSize = 13.sp
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            ChartComponent(
                statisticState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.top_spending),
                        style = ReplacementTheme.typography.titleMedium.copy(
                            color = ReplacementTheme.colorScheme.onBackground
                        )
                    )
                    IconButton(
                        onClick = {
                            statisticViewModel.onEvent(StatisticEvent.SortTransactions)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = stringResource(id = R.string.sort)
                        )
                    }
                }
                LazyColumn {
                    items(statisticState.transactions) {
                        TransactionItem(
                            transactionEntity = it,
                            modifier = Modifier.clickable {
                                navHostController.navigate(
                                    Route.AddEditTransactionScreen.getRoute(
                                        it.id
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(apiLevel = 32)
@Composable
fun StatisticScreenPreview() {
    StatisticScreen(rememberNavController())
}