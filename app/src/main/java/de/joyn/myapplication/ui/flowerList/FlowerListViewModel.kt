package de.joyn.myapplication.ui.flowerList

import de.joyn.myapplication.domain.entity.FlowerModel
import de.joyn.myapplication.domain.interactor.GetFlowerUseCase
import de.joyn.myapplication.ui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class FlowerListViewModel @Inject constructor(val getFlowerUseCase: GetFlowerUseCase) :
    BaseViewModel<FlowerListViewState>() {

    init {
        getFlowers()
    }

    private fun getFlowers(){
        val disposable = getFlowerUseCase.execute(FlowerModel()).subscribe({ response->
            Timber.i("emitter size is"+response.size)
            stateLiveData.postValue(FlowerListViewState(response))
        },{t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)
    }

}