package de.joyn.myapplication.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
    }
}