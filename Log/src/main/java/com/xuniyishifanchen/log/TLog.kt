package com.xuniyishifanchen.log

import timber.log.TLogTree
import timber.log.Timber

object TLog {

    fun init() {
        Timber.plant(TLogTree())
    }

    fun d(message: String, vararg args: Any) {
        Timber.d(message, args)
    }


}