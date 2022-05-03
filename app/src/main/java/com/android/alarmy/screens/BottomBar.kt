package com.android.alarmy.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.alarmy.navigation.AlarmyScreens
import com.android.alarmy.utils.AppColors

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        AlarmyScreens.Home,
        AlarmyScreens.Record,
        AlarmyScreens.Panel,
        AlarmyScreens.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color(0xFFEDF1FA),
        contentColor = Color.Black
    ) {
        screens.forEach { screen ->
            AddItem(
                screens = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screens: AlarmyScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selectedContentColor = AppColors.MayaBlue,
        unselectedContentColor = Color.Black,
        label = {
            Text(text = screens.title)
        },
        alwaysShowLabel = false,
        icon = {
            Icon(
                imageVector = screens.icon,
                contentDescription = null,
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screens.route
        } == true,
        onClick = {
            navController.navigate(screens.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Preview
@Composable
fun Pv1() {
    val navController = rememberNavController()
    BottomBar(navController = navController)
}