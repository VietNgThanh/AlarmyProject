package com.android.alarmy.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.android.alarmy.utils.AppColors

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.Solitude),
    ) {
        Text(
            text = "Settings Screen",
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Color.Black
        )
    }
}