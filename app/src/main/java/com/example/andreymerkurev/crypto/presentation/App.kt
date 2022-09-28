package com.example.andreymerkurev.crypto.presentation

import android.app.Application
import com.example.andreymerkurev.crypto.presentation.di.Injector

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.createAppComponent(this)
    }
}