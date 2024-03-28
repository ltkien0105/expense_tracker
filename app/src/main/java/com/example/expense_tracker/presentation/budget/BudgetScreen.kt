package com.example.expense_tracker.presentation.budget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.R
import com.example.expense_tracker.domain.model.CategoriesItem
import com.example.expense_tracker.presentation.budget.components.CreateBudgetButton
import com.example.expense_tracker.presentation.budget.components.InfoBudget
import com.example.expense_tracker.presentation.budget.components.InformationBudgetCard
import com.example.expense_tracker.presentation.budget.components.drawBudgetArc
import com.example.expense_tracker.presentation.navgraph.Route
import com.example.expense_tracker.ui.theme.DarkGreen
import com.example.expense_tracker.ui.theme.Gray
import com.example.expense_tracker.ui.theme.ReplacementTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BudgetScreen(
    navHostController: NavHostController,
    budgetViewModel: BudgetViewModel = hiltViewModel()
) {

    val budgetState by budgetViewModel.budgetState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = ReplacementTheme.colorScheme.primary)
    ) {
        if (budgetState.budgetList.isEmpty()) {
            Text(
                text = stringResource(R.string.empty_budget),
                style = ReplacementTheme.typography.displaySmall.copy(
                    fontSize = 16.sp,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            CreateBudgetButton { /*TODO*/ }
        } else {
            val canvasSize = 250.dp
            val boxHeight = canvasSize + 32.dp

            val angle by animateFloatAsState(
                targetValue = 180f * (budgetState.totalSpent.toFloat() / budgetState.totalBudget.toFloat()),
                animationSpec = tween(
                    durationMillis = 1500,
                    easing = FastOutSlowInEasing
                ),
                label = "angle"
            )

            Box(
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    .shadow(
                        shape = RoundedCornerShape(16.dp),
                        elevation = 1.dp
                    )
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(boxHeight)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Canvas(
                    modifier = Modifier
                        .size(canvasSize)
                        .align(Alignment.Center)
                ) {
                    drawBudgetArc(180f, Color(0xFFe9e9ff))
                    drawArc(
                        brush = Brush.linearGradient(
                            0.0f to Color(0xFF8737d6).copy(
                                alpha = 0.3f
                            ),
                            1.0f to Color(0xFF8737d6),
                            start = Offset(0f, center.y),
                            end = Offset(size.width, center.y)
                        ),
                        startAngle = -180f,
                        sweepAngle = angle,
                        useCenter = false,
                        size = size,
                        style = Stroke(
                            width = 10.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                    rotate(
                        degrees = angle
                    ) {
                        drawCircle(
                            color = Color(0xFF8737d6),
                            radius = 30f,
                            center = Offset(0f, center.y)
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(boxHeight)
                        .padding(bottom = 8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                    ) {
                        Text(
                            stringResource(R.string.amount_spend),
                            style = ReplacementTheme.typography.labelSmall
                        )
                        Text(
                            budgetState.totalBudget.minus(budgetState.totalSpent).toString(),
                            textAlign = TextAlign.Center,
                            style = ReplacementTheme.typography.displaySmall.copy(
                                fontSize = 30.sp
                            ),
                            modifier = Modifier.fillMaxWidth(0.7f)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    bottom = 16.dp
                                )
                        ) {
                            InfoBudget(
                                amount = budgetState.totalBudget,
                                title = stringResource(R.string.total_budgets)
                            )
                            VerticalDivider(
                                color = Gray,
                                thickness = Dp.Hairline,
                                modifier = Modifier.height(40.dp)
                            )
                            InfoBudget(
                                amount = budgetState.totalSpent,
                                title = stringResource(R.string.total_spent)
                            )
                            VerticalDivider(
                                color = Gray,
                                thickness = Dp.Hairline,
                                modifier = Modifier.height(40.dp)
                            )
                            InfoBudget(
                                amount = budgetState.dayRemaining,
                                title = stringResource(R.string.end_of_month, "month")
                            )
                        }
                        CreateBudgetButton {
                            navHostController.navigate(Route.AddEditBudgetScreen.getRoute(0))
                        }
                    }
                }

            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f)
            ) {
                items(budgetState.budgetList) {
                    InformationBudgetCard(
                        categoryItem = enumValueOf<CategoriesItem>(it.category),
                        totalBudget = it.totalAmount,
                        leftBudget = it.leftAmount,
                        modifier = Modifier.clickable {
                            navHostController.navigate(Route.AddEditBudgetScreen.getRoute(it.id))
                        }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewBudgetScreen() {
    BudgetScreen(
        navHostController = rememberNavController()
    )
}