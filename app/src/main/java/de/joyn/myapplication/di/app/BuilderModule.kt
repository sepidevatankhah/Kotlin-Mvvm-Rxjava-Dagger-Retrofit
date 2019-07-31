package de.joyn.myapplication.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.joyn.myapplication.ui.flowerList.FlowerListActivity
import de.joyn.myapplication.ui.flowerList.FlowerListActivityModule

@Module()
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [FlowerListActivityModule::class])
    abstract fun bindFlowerListActivity(): FlowerListActivity
}