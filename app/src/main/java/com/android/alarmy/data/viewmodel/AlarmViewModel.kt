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
                    _alarmList.value = sortAlarm(it)
                }
            }
        }
    }

    fun addAlarm(alarm: Alarm) = viewModelScope.launch { repository.addAlarm(alarm) }
    fun updateAlarm(alarm: Alarm) = viewModelScope.launch { repository.updateAlarm(alarm) }
    fun deleteAlarm(alarm: Alarm) = viewModelScope.launch { repository.deleteAlarm(alarm) }

//    fun changeTaskChecked(alarm: Alarm, checked: Boolean) = viewModelScope.launch {
//        _alarmList.value.find {
//            it.id == alarm.id
//        }?.let {
//            it.state = checked
//            repository.updateAlarm(it)
//        }
//    }

    fun changeTaskChecked(alarmId: String, checked: Boolean) = viewModelScope.launch {
        val alarm = findAlarmById(alarmId) ?: return@launch
        alarm.state = checked
        updateAlarm(alarm)
    }

    fun findAlarmById(alarmId: String): Alarm? =
        _alarmList.value.find {
            it.id.toString() == alarmId
        }

    private fun sortAlarm(alarmList: List<Alarm>): List<Alarm> {
        return alarmList.sortedWith(compareBy<Alarm> {
            it.state
        }
            .reversed()
            .thenBy { it.hour }
            .thenBy { it.minute }
        )
    }

    fun findNextAlarm(): Alarm {
        TODO("Not yet implemented")
    }
}