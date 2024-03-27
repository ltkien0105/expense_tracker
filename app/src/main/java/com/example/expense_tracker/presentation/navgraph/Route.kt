package com.example.expense_tracker.presentation.navgraph

import androidx.annotation.DrawableRes
import com.example.expense_tracker.R

sealed class Route(
    val route: String
) {
    sealed class BottomNavigationScreen(
        @DrawableRes val unselectedIcon: Int,
        @DrawableRes val selectedIcon: Int,
        route: String
    ): Route(route) {
        data object Home: BottomNavigationScreen(
            unselectedIcon = R.drawable.ic_home,
            selectedIcon = R.drawable.ic_home_filled,
            route = "home"
        )
        data object Statistic: BottomNavigationScreen(
            unselectedIcon = R.drawable.ic_bar_chart,
            selectedIcon = R.drawable.ic_bar_chart_filled,
            route = "statistic"
        )
        data object Budget: BottomNavigationScreen(
            unselectedIcon = R.drawable.ic_wallet,
            selectedIcon = R.drawable.ic_wallet_filled,
            route = "budget"
        )
        data object Settings: BottomNavigationScreen(
            unselectedIcon = R.drawable.ic_user,
            selectedIcon = R.drawable.ic_user_filled,
            route = "settings"
        )
    }

    data object AddEditTransactionScreen: Route("add_edit_transaction/{id}") {
        fun getRoute(id: Int) = "add_edit_transaction/$id"
    }

    data object AddEditBudgetScreen: Route("add_edit_budget/{id}") {
        fun getRoute(id: Int) = "add_edit_budget/$id"
    }
}

val bottomNavigationScreen = listOf(
    Route.BottomNavigationScreen.Home,
    Route.BottomNavigationScreen.Statistic,
    Route.BottomNavigationScreen.Budget,
    Route.BottomNavigationScreen.Settings,
)