package com.android.alarmy.di

import android.content.Context
import androidx.room.Room
import com.android.alarmy.data.local.AlarmDatabase
import com.android.alarmy.data.local.AlarmDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAlarmDatabaseDao(alarmDatabase: AlarmDatabase): AlarmDatabaseDao =
        alarmDatabase.alarmDatabaseDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AlarmDatabase =
        Room.databaseBuilder(context, AlarmDatabase::class.java, "alarm_db")
            .fallbackToDestructiveMigration()
            .build()
}