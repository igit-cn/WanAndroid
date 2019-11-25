package com.zhuzichu.android.shared.global

import android.app.Application
import android.content.Context

object AppGlobal {

    private lateinit var application: Application
    lateinit var loginClazz: Class<*>

    val context: Context by lazy {
        application.applicationContext
    }

    fun init(application: Application): AppGlobal {
        AppGlobal.application = application
        return this
    }

}