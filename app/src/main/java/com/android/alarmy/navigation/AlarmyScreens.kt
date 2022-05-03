package com.android.alarmy.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessAlarm
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AlarmyScreens(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home: AlarmyScreens(
        route = "home",
        title = "Home",
        icon = Icons.Outlined.AccessAlarm,
    )

    object Record: AlarmyScreens(
        route = "stats",
        title = "Stats",
        icon = Icons.Outlined.BarChart,
    )

    object Panel: AlarmyScreens(
        route = "panel",
        title = "Panel",
        icon = Icons.Outlined.Article,
    )

    object Settings: AlarmyScreens(
        route = "settings",
        title = "Settings",
        icon = Icons.Outlined.Settings,
    )
}
