package com.example.expense_tracker.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expense_tracker.R
import com.example.expense_tracker.ui.theme.ReplacementTheme
import com.example.expense_tracker.ui.theme.WhiteAlpha15

@Composable
fun CardInformation(
    totalBalance: Double,
    income: Double,
    expense: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = ReplacementTheme.colorScheme.primary,
            contentColor = ReplacementTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(id = R.string.total_balance),
                            style = MaterialTheme
                                .typography
                                .titleMedium
                                .copy(fontSize = 16.sp)
                        )
                        IconButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.size(30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = null
                            )
                        }
                    }
                    Text(
                        totalBalance.toString(),
                        style = ReplacementTheme.typography.bodySuperLarge
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                        contentDescription = stringResource(id = R.string.more)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IncomeExpenseField(
                    R.drawable.ic_income,
                    label = stringResource(id = R.string.income),
                    incomeExpenseValue = income
                )
                IncomeExpenseField(
                    R.drawable.ic_expense,
                    label = stringResource(id = R.string.expense),
                    incomeExpenseValue = expense
                )
            }
        }
    }
}

@Composable
fun IncomeExpenseField(
    @DrawableRes icon: Int,
    label: String,
    incomeExpenseValue: Double
) {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .background(
                        color = WhiteAlpha15,
                        shape = CircleShape
                    )
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                color = Color(0xFFD0E5E4),
                style = ReplacementTheme
                    .typography
                    .labelLarge
                    .copy(fontWeight = FontWeight.Medium)
            )
        }
        Text(
            text = incomeExpenseValue.toString(),
            style = ReplacementTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview
@Composable
fun CardInformationPreview() {
    CardInformation(
        5000.0,
        5000.0,
        5000.0
    )
}