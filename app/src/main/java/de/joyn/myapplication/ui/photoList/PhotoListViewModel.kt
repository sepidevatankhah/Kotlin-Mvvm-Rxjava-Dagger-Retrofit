package de.joyn.myapplication.ui.photoList

import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.ui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class PhotoListViewModel @Inject constructor(private val getFlowerUseCase: GetPhotoUseCase) :
    BaseViewModel<PhotoListViewState>() {

    init {
        getPhotos("flowers")
    }

     fun getPhotos(filter: String?){
        val disposable = getFlowerUseCase.execute(filter).subscribe({ response->
            Timber.i("emitter size is"+response.size)
            stateLiveData.postValue(PhotoListViewState(response))
        },{t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)
    }


}