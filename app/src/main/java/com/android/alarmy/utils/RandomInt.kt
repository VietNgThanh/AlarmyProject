package com.android.alarmy.utils

import java.util.concurrent.atomic.AtomicInteger

object RandomInt {
    private val seed = AtomicInteger()

    fun getRandomInt(): Int = seed.getAndIncrement() + System.currentTimeMillis().toInt()
}