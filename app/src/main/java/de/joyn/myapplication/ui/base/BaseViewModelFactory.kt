package de.joyn.myapplication.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelFactory<S : BaseViewState>
protected constructor():ViewModelProvider.Factory
{
    protected val mToastLiveData: SingleLiveData<Int> = SingleLiveData()
    protected val mStateLD: MutableLiveData<S> = MutableLiveData()
}