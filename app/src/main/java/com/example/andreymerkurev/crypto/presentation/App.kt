package com.example.andreymerkurev.crypto.presentation

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Injector.createAppComponent(this)
    }
}