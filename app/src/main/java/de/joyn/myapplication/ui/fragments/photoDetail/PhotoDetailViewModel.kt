package de.joyn.myapplication.ui.fragments.photoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.joyn.myapplication.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * ViewModel for the photo detail showing the details
 */
class PhotoDetailViewModel @Inject constructor() : BaseViewModel<Boolean>() {
    private val _imgLargePhoto = MutableLiveData<String>()
    val imgLargePhoto: LiveData<String>
        get() = _imgLargePhoto

    private val _tags = MutableLiveData<String>()
    val tags: LiveData<String>
        get() = _tags

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    fun setData(
        tags: String,
        userName: String,
        imgLargePhoto: String
        ) {
        this._imgLargePhoto.value = imgLargePhoto
        this._tags.value = tags
        this._userName.value = userName
    }
}