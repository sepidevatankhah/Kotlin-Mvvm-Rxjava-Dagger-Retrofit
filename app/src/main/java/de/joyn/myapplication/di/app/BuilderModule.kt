package de.joyn.myapplication.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.joyn.myapplication.ui.MainActivity


@Module()
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

}