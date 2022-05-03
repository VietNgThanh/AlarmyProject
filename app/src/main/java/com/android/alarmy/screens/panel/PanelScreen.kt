package com.android.alarmy.screens.panel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.android.alarmy.utils.AppColors

@Composable
fun PanelScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.Solitude),
    ) {
        Text(
            text = "Panel Screen",
            fontSize = MaterialTheme.typography.h3.fontSize,
            color = Color.Black
        )
    }
}