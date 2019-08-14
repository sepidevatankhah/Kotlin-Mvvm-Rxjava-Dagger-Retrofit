package de.joyn.myapplication.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * The ViewModel’s key responsibility is to expose states for the View
 *to consume to ensure the View receives the latest data
 */

abstract class BaseViewModel<M> : ViewModel() {

    var stateLiveData: LiveData<M> = MutableLiveData()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}