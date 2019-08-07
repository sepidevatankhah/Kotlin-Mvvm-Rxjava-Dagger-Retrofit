package de.joyn.myapplication

import android.app.Application
import dagger.android.HasActivityInjector
import de.joyn.myapplication.di.app.AppComponent
import de.joyn.myapplication.di.app.DaggerAppComponent
import timber.log.Timber


class App : Application(), HasActivityInjector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        timber()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    private fun timber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector() = appComponent.activityInjector
}