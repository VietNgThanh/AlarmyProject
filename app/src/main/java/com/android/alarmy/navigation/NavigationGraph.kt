package com.android.alarmy.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.android.alarmy.data.viewmodel.AlarmViewModel
import com.android.alarmy.model.Alarm
import com.android.alarmy.screens.home.AddAlarmScreen
import com.android.alarmy.screens.home.HomeScreen
import com.android.alarmy.screens.panel.PanelScreen
import com.android.alarmy.screens.settings.SettingsScreen
import com.android.alarmy.screens.stats.RecordScreen

@Composable
fun NavigationGraph(navController: NavHostController, alarmViewModel: AlarmViewModel) {
    NavHost(navController = navController, startDestination = AlarmyScreens.Home.route) {
        composable(route = AlarmyScreens.Home.route) {
            HomeScreen(navController, alarmViewModel, onDeleteAlarm = {
                alarmViewModel.deleteAlarm(it)
            })
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

        composable(route = SubAlarmScreens.AddAlarmScreen.name) {
            AddAlarmScreen(
                navController = navController,
                alarm = Alarm(),
                onSaveAlarm = {
                    alarmViewModel.addAlarm(it)
                },
                onDeleteAlarm = {
                    alarmViewModel.deleteAlarm(it)
                },
            )
            Log.d("NavigationGraph", "Triggered")
        }

        composable(
            route = SubAlarmScreens.EditAlarmScreen.name + "/{alarmId}",
            arguments = listOf(navArgument(name = "alarmId") { type = NavType.StringType })
        ) {
//            val gson = Gson()
            it.arguments?.getString("alarmId")?.let { alarmId ->
//                val alarm = gson.fromJson(it, Alarm::class.java)
                AddAlarmScreen(
                    navController = navController,
                    alarm = alarmViewModel.findAlarmById(alarmId) ?: Alarm(),
                    onSaveAlarm = {
                        alarmViewModel.addAlarm(it)
                    },
                    onDeleteAlarm = {
                        alarmViewModel.deleteAlarm(it)
                    },
                )
            }
        }
    }
}