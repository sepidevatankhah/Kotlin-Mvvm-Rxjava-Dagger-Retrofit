package de.joyn.myapplication.ui.fragments.photoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.joyn.myapplication.ui.BaseViewModel

class PhotoDetailViewModel : BaseViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status
}