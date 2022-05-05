package com.android.alarmy.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.alarmy.model.Alarm
import com.android.alarmy.repository.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(private val repository: AlarmRepository) : ViewModel() {
    private val _alarmList = MutableStateFlow<List<Alarm>>(emptyList())
    val alarmList = _alarmList.asStateFlow()
//    var singleAlarm: Alarm? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllAlarms().distinctUntilChanged().collect() {
                it.let {
                    _alarmList.value = it
                }
            }
        }
    }

    fun addAlarm(alarm: Alarm) = viewModelScope.launch { repository.addAlarm(alarm) }
    fun updateAlarm(alarm: Alarm) = viewModelScope.launch { repository.updateAlarm(alarm) }
    fun deleteAlarm(alarm: Alarm) = viewModelScope.launch { repository.deleteAlarm(alarm) }
//    fun findAlarmById(id: String) = viewModelScope.launch {
//        singleAlarm = repository.findAlarmById(id)
//    }
}