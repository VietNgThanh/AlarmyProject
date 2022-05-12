package com.android.alarmy.screens.addalarm.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.alarmy.model.Alarm

@Composable
fun AlarmCardList(
    alarmList: List<Alarm>,
    onCardClick: (String) -> Unit,
    onCardCheckedChange: (Alarm, Boolean) -> Unit,
    onCardDelete: (Alarm) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = modifier
    ) {
        items(
            items = alarmList,
            key = {
                it.id
            }
        ) {alarm ->
            AlarmCard(
                alarm = alarm,
                onDeleteAlarm = onCardDelete,
                onItemClick = onCardClick,
                onCheckedChange = { onCardCheckedChange(alarm, it) },
            )
        }
    }
}