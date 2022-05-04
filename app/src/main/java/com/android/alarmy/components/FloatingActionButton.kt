package com.android.alarmy.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.android.alarmy.utils.AppColors

enum class FloatingActionButtonState {
    EXPANDED,
    COLLAPSED
}

data class FabItem(
    val label: String,
    val icon: ImageVector,
    val onClick: (FabItem) -> Unit
)


@Composable
fun MyFloatingActionButton(
    fabItemList: List<FabItem>,
    floatingActionButtonState: FloatingActionButtonState,
    onStateChange: (FloatingActionButtonState) -> Unit,
) {
    val transition = updateTransition(targetState = floatingActionButtonState, label = "transition")
    val rotate by transition.animateFloat(label = "rotate") {
        if (it == FloatingActionButtonState.EXPANDED) {
            45f
        } else {
            0f
        }
    }
    Column(
        horizontalAlignment = Alignment.End,
    ) {
        if (floatingActionButtonState == FloatingActionButtonState.EXPANDED) {
            fabItemList.forEach { fabItem ->
                FabItemButton(fabItem = fabItem)
            }
        }
        FloatingActionButton(
            onClick = {
                onStateChange(
                    if (floatingActionButtonState == FloatingActionButtonState.EXPANDED) {
                        FloatingActionButtonState.COLLAPSED
                    } else {
                        FloatingActionButtonState.EXPANDED
                    }
                )
            },
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.rotate(rotate)
            )
        }
    }
}

@Composable
fun FabItemButton(fabItem: FabItem) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .width(200.dp)
            .padding(end = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = AppColors.Solitude)
        ) {
            Text(
                text = fabItem.label,
                modifier = Modifier.padding(8.dp)
            )
        }
        IconButton(
            onClick = { fabItem.onClick.invoke(fabItem) },
            modifier = Modifier
                .size(50.dp)
                .padding(start = 12.dp)
        ) {
            Icon(
                imageVector = fabItem.icon,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
        }
    }

}