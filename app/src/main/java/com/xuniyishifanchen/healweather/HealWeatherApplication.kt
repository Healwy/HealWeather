package com.xuniyishifanchen.healweather

import android.app.Application
import android.content.Context
import com.xuniyishifanchen.log.TLog

class HealWeatherApplication : Application() {

    companion object {
        lateinit var context: Context
        const val TOKEN = "OxKQdsd3tLZpffrn" // add caiyun weather token
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        TLog.init()
    }
}