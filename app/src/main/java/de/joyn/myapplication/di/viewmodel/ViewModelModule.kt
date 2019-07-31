package de.joyn.myapplication.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import de.joyn.myapplication.ui.flowerList.FlowerListViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(clazz = FlowerListViewModel::class)
    abstract fun bindFlowerListViewModel(viewModel: FlowerListViewModel): ViewModel
}