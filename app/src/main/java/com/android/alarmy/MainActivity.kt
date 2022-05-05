package com.android.alarmy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAlarm
import androidx.compose.material.icons.outlined.OfflineBolt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.alarmy.components.BottomBar
import com.android.alarmy.components.FabItem
import com.android.alarmy.components.FloatingActionButtonState
import com.android.alarmy.components.MyFloatingActionButton
import com.android.alarmy.data.viewmodel.AlarmViewModel
import com.android.alarmy.navigation.AlarmyScreens
import com.android.alarmy.navigation.NavigationGraph
import com.android.alarmy.navigation.SubAlarmScreens
import com.android.alarmy.ui.theme.AlarmyTheme
import com.android.alarmy.utils.AppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmyTheme {
                AlarmyApp()
            }
        }
    }
}

@Composable
fun AlarmyApp() {
    val alarmViewModel = viewModel<AlarmViewModel>()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val fabState = remember {
        mutableStateOf(FloatingActionButtonState.COLLAPSED)
    }
    val fabItemList = listOf(
        FabItem(
            label = "Quick Alarm",
            icon = Icons.Outlined.OfflineBolt,
            onClick = {}
        ),
        FabItem(
            label = "Alarm",
            icon = Icons.Outlined.AddAlarm,
            onClick = {
                navController.navigate(route = SubAlarmScreens.AddAlarmScreen.name)
            }
        )
    )
    Scaffold(
        bottomBar = {
            when (currentRoute) {
                AlarmyScreens.Home.route,
                AlarmyScreens.Record.route,
                AlarmyScreens.Panel.route,
                AlarmyScreens.Settings.route
                -> BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            when (currentRoute) {
                AlarmyScreens.Home.route,
                AlarmyScreens.Record.route,
                AlarmyScreens.Panel.route,
                AlarmyScreens.Settings.route
                -> {
                    MyFloatingActionButton(
                        fabItemList = fabItemList,
                        floatingActionButtonState = fabState.value,
                        onStateChange = {
                            fabState.value = it
                        }
                    )
                }
            }

//            FloatingActionButton(
//                onClick = {},
//                backgroundColor = Color.Red,
//                content = {
//                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
//                }
//            )
        },
        backgroundColor = AppColors.AliceBlue
    ) {
        NavigationGraph(navController = navController, alarmViewModel = alarmViewModel )
    }
}
