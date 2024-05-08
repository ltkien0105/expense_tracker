package com.example.expense_tracker.presentation.navgraph

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.expense_tracker.presentation.add_edit_budget.AddEditBudgetScreen
import com.example.expense_tracker.presentation.add_edit_transaction.AddEditTransactionScreen
import com.example.expense_tracker.presentation.budget.BudgetScreen
import com.example.expense_tracker.presentation.home.HomeScreen
import com.example.expense_tracker.presentation.settings.SettingsScreen
import com.example.expense_tracker.presentation.statistic.StatisticScreen

@SuppressLint("NewApi")
@Composable
fun Navigation(
    snackbarHostState: SnackbarHostState,
    navHostController: NavHostController = rememberNavController(),
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.BottomNavigationScreen.Home.route,
        modifier = Modifier.padding(
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        )
    ) {
        composable(
            Route.BottomNavigationScreen.Home.route
        ) {
            HomeScreen(
                navHostController = navHostController
            )
        }

        composable(
            Route.AddEditTransactionScreen.route,
            arguments = listOf(
                navArgument("id") {
                    defaultValue = 0
                }
            )
        ) {
            val id = it.arguments?.getInt("id") ?: 0
            AddEditTransactionScreen(
                snackbarHostState = snackbarHostState,
                id = id
            )
        }

        composable(Route.BottomNavigationScreen.Statistic.route) {
            StatisticScreen(navHostController = navHostController)
        }

        navigation(
            route = "budget_route",
            startDestination = Route.BottomNavigationScreen.Budget.route
        ) {
            composable(Route.BottomNavigationScreen.Budget.route) {
                BudgetScreen(
                    navHostController = navHostController
                )
            }
            composable(
                Route.AddEditBudgetScreen.route,
                arguments = listOf(
                    navArgument("id") {
                        defaultValue = 0
                    }
                )
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                AddEditBudgetScreen(
                    snackbarHostState = snackbarHostState,
                    id = id
                )
            }
        }
        composable(Route.BottomNavigationScreen.Settings.route) {
            SettingsScreen()
        }
    }
}