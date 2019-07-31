package de.joyn.myapplication.ui.photoList

import de.joyn.myapplication.domain.entity.FlowerModel
import de.joyn.myapplication.domain.interactor.GetFlowerUseCase
import de.joyn.myapplication.ui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class PhotoListViewModel @Inject constructor(val getFlowerUseCase: GetFlowerUseCase) :
    BaseViewModel<PhotoListViewState>() {

    init {
        getFlowers()
    }

    private fun getFlowers(){
        val disposable = getFlowerUseCase.execute(FlowerModel()).subscribe({ response->
            Timber.i("emitter size is"+response.size)
            stateLiveData.postValue(PhotoListViewState(response))
        },{t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)
    }

}