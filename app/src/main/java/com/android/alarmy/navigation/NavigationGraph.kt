package com.android.alarmy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.alarmy.navigation.AlarmyScreens
import com.android.alarmy.screens.home.HomeScreen
import com.android.alarmy.screens.panel.PanelScreen
import com.android.alarmy.screens.stats.RecordScreen
import com.android.alarmy.screens.settings.SettingsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AlarmyScreens.Home.route) {
        composable(route = AlarmyScreens.Home.route) {
            HomeScreen()
        }

        composable(route = AlarmyScreens.Record.route) {
            RecordScreen()
        }

        composable(route = AlarmyScreens.Panel.route) {
            PanelScreen()
        }

        composable(route = AlarmyScreens.Settings.route) {
            SettingsScreen()
        }
    }
}