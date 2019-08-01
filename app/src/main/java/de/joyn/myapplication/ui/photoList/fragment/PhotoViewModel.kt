package de.joyn.myapplication.ui.photoList.fragment

//class PhotoViewModel @Inject constructor(val getFlowerUseCase: GetPhotoUseCase) :
//    BaseViewModel<PhotoViewState>() {
//
//    init {
//        getFlowers()
//    }
//
//    private fun getFlowers(){
//        val disposable = getFlowerUseCase.execute(FlowerModel()).subscribe({ response->
//            Timber.i("emitter size is"+response.size)
//            stateLiveData.postValue(PhotoViewState(response))
//        },{t: Throwable? ->
//            Timber.e(t)
//        })
//        compositeDisposable.add(disposable)
//    }
//
//}