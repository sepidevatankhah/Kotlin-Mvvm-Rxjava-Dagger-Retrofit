package de.joyn.myapplication.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.joyn.myapplication.ui.photoDetail.PhotoDetailActivity
import de.joyn.myapplication.ui.photoDetail.PhotoDetailModule
import de.joyn.myapplication.ui.main.MainActivity
import de.joyn.myapplication.ui.main.MainActivityModule
import de.joyn.myapplication.ui.photoList.PhotoListActivity
import de.joyn.myapplication.ui.photoList.PhotoListActivityModule


@Module()
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [PhotoListActivityModule::class])
    abstract fun bindFlowerListActivity(): PhotoListActivity

    @ContributesAndroidInjector(modules = [PhotoDetailModule::class])
    abstract fun bindFlowerDetailActivity(): PhotoDetailActivity

//    @ContributesAndroidInjector(modules = [PhotoFragmentModule::class])
//    abstract fun bindFlowerListFragment(): FlowerListFragment


    @ContributesAndroidInjector(modules = [MainActivityModule::class])
        //,        FlowerFragmentProvider::class])
     abstract fun bindMainActivity(): MainActivity
}