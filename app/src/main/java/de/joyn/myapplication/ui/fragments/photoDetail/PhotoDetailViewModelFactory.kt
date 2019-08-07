package de.joyn.myapplication.ui.fragments.photoDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhotoDetailViewModelFactory : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoDetailViewModel::class.java)) {
            return PhotoDetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}