package de.joyn.myapplication.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import de.joyn.myapplication.App
import de.joyn.myapplication.data.extractor.NetworkJobExecutor
import de.joyn.myapplication.di.scope.ForApplication
import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import de.joyn.myapplication.ui.executer.UiThreadExecutor
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideUseCaseExecutor(): UseCaseExecutor {
        return NetworkJobExecutor()
    }

    @Provides
    @Singleton
    fun postExecutionThread(): PostExecutionThread = UiThreadExecutor()


    @Provides
    @ForApplication
    fun provideContext(app: App): Context = app.applicationContext
}
