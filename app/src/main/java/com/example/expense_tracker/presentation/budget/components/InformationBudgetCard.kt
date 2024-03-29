package com.example.expense_tracker.presentation.budget.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.R
import com.example.expense_tracker.domain.model.CategoriesItem
import com.example.expense_tracker.ui.theme.Gray
import com.example.expense_tracker.ui.theme.ReplacementTheme
import kotlin.math.min

@Composable
fun InformationBudgetCard(
    categoryItem: CategoriesItem,
    totalBudget: Double,
    leftBudget: Double,
    modifier: Modifier = Modifier
) {
    val lineColor = ReplacementTheme.colorScheme.onBackground
    Card(
        colors = CardDefaults.cardColors(
            containerColor = ReplacementTheme.colorScheme.background,
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(
                        painter = painterResource(id = categoryItem.icon),
                        contentDescription = stringResource(id = categoryItem.category),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = stringResource(id = categoryItem.category),
                        color = ReplacementTheme.colorScheme.onBackground
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = stringResource(R.string.total, totalBudget),
                        color = ReplacementTheme.colorScheme.onBackground
                    )
                    Text(
                        text = stringResource(R.string.left, leftBudget),
                        color = ReplacementTheme.colorScheme.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            ) {
                drawLine(
                    color = lineColor,
                    start = Offset.Zero,
                    end = Offset(size.width, 0f),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.Magenta,
                    start = Offset.Zero,
                    end = Offset(min(size.width, size.width * (1f - (leftBudget / totalBudget).toFloat())), 0f),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewInformationBudgetCard() {
    InformationBudgetCard(
        categoryItem = CategoriesItem.Allowance,
        totalBudget = 1000000.0,
        leftBudget = 500000.0
    )
}