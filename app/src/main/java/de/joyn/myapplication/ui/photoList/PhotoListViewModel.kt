package de.joyn.myapplication.ui.photoList

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import de.joyn.myapplication.domain.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewModel2
import timber.log.Timber
import javax.inject.Inject

class PhotoListViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    dataSourceFactory: PhotoDataSourceFactory
) :
    BaseViewModel2<PhotoListViewState>() {

    private val PAGE_SIZE = 10
    private val INITIAL_LOAD_SIZE_HINT = 20
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()

    lateinit var photoList: LiveData<PagedList<Models.PhotoResponse>>


    init {
        photoList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
        Timber.d("photoList : %s", photoList)
        //getPhotos("flowers")
    }

    fun getPhotos(filter: String?) {
        val disposable = getPhotoUseCase.execute(PhotoModel()).subscribe({ response ->
            // Timber.i("emitter size is"+response.size)
            // stateLiveData.postValue(PhotoListViewState(response.response))
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)
    }


}