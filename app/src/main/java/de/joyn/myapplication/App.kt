package de.joyn.myapplication

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import de.joyn.myapplication.di.app.DaggerAppComponent
import timber.log.Timber


class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        timber()
    }

    private fun timber() {
        Timber.plant(Timber.DebugTree())
    }
}