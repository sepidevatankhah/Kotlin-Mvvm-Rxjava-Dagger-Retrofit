package de.joyn.myapplication.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import de.joyn.myapplication.App
import de.joyn.myapplication.data.extractor.NetworkJobExecutor
import de.joyn.myapplication.di.scope.ForApplication
import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import de.joyn.myapplication.domain.repository.ConnectivityManager
import de.joyn.myapplication.ui.executer.UiThreadExecutor
import de.joyn.myapplication.util.ConnectivityManagerImp
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideUseCaseExecutor(): UseCaseExecutor {
        return NetworkJobExecutor()
    }

    @Provides
    @JvmStatic
    @Singleton
    fun postExecutionThread(): PostExecutionThread = UiThreadExecutor()


    @Provides
    @JvmStatic
    @ForApplication
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    @JvmStatic
    @Singleton
    fun provideConnectivityManager(connectivityManagerImp: ConnectivityManagerImp)
            : ConnectivityManager = connectivityManagerImp
}
