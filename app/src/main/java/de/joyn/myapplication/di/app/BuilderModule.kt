package de.joyn.myapplication.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.joyn.myapplication.ui.MainActivity

//The BuilderModule above binds the MainActivityModule with the MainActivity
//No longer need to provide a Component for the MainActivityModule
// or any other activity level Dagger Module

@Module()
abstract class BuilderModule {

    //We bound the MainActivityModule to the MainActivity using a @ContributesAndroidInjector
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

}