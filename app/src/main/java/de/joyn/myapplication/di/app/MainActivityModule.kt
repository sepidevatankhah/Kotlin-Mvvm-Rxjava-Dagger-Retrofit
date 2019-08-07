package de.joyn.myapplication.di.app

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import de.joyn.myapplication.domain.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.ui.MainActivity
import de.joyn.myapplication.ui.fragments.photos.PhotosFragment
import de.joyn.myapplication.ui.fragments.photos.PhotosViewModelFactory

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributePhotosFragment(): PhotosFragment

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providePhotosViewModelFactory(dataSourceFactory: PhotoDataSourceFactory) =
            PhotosViewModelFactory(dataSourceFactory)

    }
}