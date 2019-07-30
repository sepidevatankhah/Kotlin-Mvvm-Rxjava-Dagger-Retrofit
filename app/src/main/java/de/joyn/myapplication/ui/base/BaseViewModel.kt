package de.joyn.myapplication.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S> : ViewModel() {

    val stateLD: MutableLiveData<S> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
    }
}