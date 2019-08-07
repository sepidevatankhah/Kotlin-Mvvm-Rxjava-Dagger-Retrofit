package de.joyn.myapplication.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel2<S> : ViewModel() {

    var stateLiveData: MutableLiveData<S> = MutableLiveData()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}