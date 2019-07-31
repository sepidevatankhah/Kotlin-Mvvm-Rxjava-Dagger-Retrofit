package de.joyn.myapplication.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.joyn.myapplication.ui.main.MainActivity
import de.joyn.myapplication.ui.main.MainActivityModule
import de.joyn.myapplication.ui.flowerList.*


@Module()
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [FlowerListActivityModule::class])
    abstract fun bindFlowerListActivity(): FlowerListActivity

//    @ContributesAndroidInjector(modules = [FlowerFragmentModule::class])
//    abstract fun bindFlowerListFragment(): FlowerListFragment


    @ContributesAndroidInjector(modules = [MainActivityModule::class])
        //,        FlowerFragmentProvider::class])
     abstract fun bindMainActivity(): MainActivity
}