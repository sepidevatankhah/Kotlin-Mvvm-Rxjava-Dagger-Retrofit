package de.joyn.myapplication.ui.fragments.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.joyn.myapplication.domain.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import javax.inject.Inject

class PhotosViewModelFactory @Inject constructor(
    private val dataSourceFactory: PhotoDataSourceFactory
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            return PhotosViewModel(dataSourceFactory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}