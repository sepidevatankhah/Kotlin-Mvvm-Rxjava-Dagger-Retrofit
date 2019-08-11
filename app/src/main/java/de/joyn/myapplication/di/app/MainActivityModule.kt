package de.joyn.myapplication.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.joyn.myapplication.ui.fragments.photoDetail.PhotoDetailFragment
import de.joyn.myapplication.ui.fragments.photos.PhotosFragment

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributePhotosFragment(): PhotosFragment

    @ContributesAndroidInjector
    abstract fun contributePhotoDetailFragment(): PhotoDetailFragment

}