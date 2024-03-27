package com.example.expense_tracker.presentation.main.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.presentation.navgraph.bottomNavigationScreen
import com.example.expense_tracker.ui.theme.DarkGreen

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 10.dp,
        modifier = Modifier.shadow(20.dp)
    ) {
        bottomNavigationScreen.forEach {
            val isSelected = currentRoute == it.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (isSelected) return@NavigationBarItem
                    navHostController.navigate(it.route)
                },
                icon = { 
                    Icon(
                        painter = painterResource(
                            id = if (isSelected)
                                    it.selectedIcon
                                else it.unselectedIcon
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = if (isSelected) DarkGreen else Color.Black
                    ) 
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}