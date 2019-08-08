package de.joyn.myapplication.ui.fragments.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import de.joyn.myapplication.domain.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 40

class PhotosViewModel @Inject constructor(
    private val dataSourceFactory: PhotoDataSourceFactory,
    private val getPhotoUseCase: GetPhotoUseCase
) : BaseViewModel() {


    fun getFilteredPhotos(filter: String) {
        dataSourceFactory.setFilter(filter)
    }

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status


    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()

    private var photoList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()

    fun getPhotoList() : LiveData <PagedList<Models.PhotoResponse>> {
        return photoList
    }

    fun recreatePhotoList() {
        photoList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }

    fun getPhotos(filter: String?) {
        val disposable = getPhotoUseCase.execute(PhotoModel(filter, 20, 1)).subscribe({ response ->
            Timber.i("emitter size is : %s", response.response)
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)
    }

}