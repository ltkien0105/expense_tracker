package com.example.expense_tracker.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.presentation.home.component.AppBarHome
import com.example.expense_tracker.presentation.main.components.BottomNavigationBar
import com.example.expense_tracker.presentation.main.components.CenteredAppBar
import com.example.expense_tracker.presentation.home.component.FAB
import com.example.expense_tracker.presentation.navgraph.Route
import com.example.expense_tracker.presentation.navgraph.Navigation

@Composable
fun MainScreen(
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(currentScreen) {
        if (snackbarHostState.currentSnackbarData != null) {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    Scaffold(
        snackbarHost = {
           SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            if (currentScreen == Route.BottomNavigationScreen.Home.route)
                AppBarHome()
            else CenteredAppBar(currentScreen)
        },
        bottomBar = { BottomNavigationBar(navHostController = navHostController) },
        floatingActionButton = {
            if (currentScreen == Route.BottomNavigationScreen.Home.route)
                FAB {
                    navHostController.navigate(Route.AddEditTransactionScreen.getRoute(0))
                }
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        paddingValues ->
        Navigation(
            snackbarHostState = snackbarHostState,
            navHostController = navHostController,
            paddingValues = paddingValues
        )
    }
}

@Preview
@Composable
fun HomePreview() {
//    MainScreen()
}