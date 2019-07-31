package de.joyn.myapplication.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.joyn.myapplication.ui.photoDetail.PhotoDetailViewModel
import de.joyn.myapplication.ui.photoList.PhotoListViewModel
import de.joyn.myapplication.ui.main.MainViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(clazz = PhotoListViewModel::class)
    abstract fun bindFlowerListViewModel(viewModel: PhotoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(clazz = PhotoDetailViewModel::class)
    abstract fun bindFlowerDetailViewModel(viewModel: PhotoDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(clazz = MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}