package de.joyn.myapplication.ui.fragments.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import de.joyn.myapplication.domain.dataSource.ApiStatus
import de.joyn.myapplication.domain.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewModel
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 40

class PhotosViewModel @Inject constructor(
    private val dataSourceFactory: PhotoDataSourceFactory
) : BaseViewModel<PagedList<Models.PhotoResponse>>() {

    /**
     * Variable that tells the Fragment to navigate to a specific [PhotoDetailFragment]
     *
     * This is private because we don't want to expose setting this value to the Fragment.
     */
    private val _navigateToPhotoDetail = MutableLiveData<Models.PhotoResponse>()
    val navigateToPhotoDetail
        get() = _navigateToPhotoDetail

    fun onPhotoClicked(photo: Models.PhotoResponse) {
        _navigateToPhotoDetail.value = photo
    }

    fun onPhotoDetailNavigated() {
        _navigateToPhotoDetail.value = null
    }

//    private val _status = MutableLiveData<ApiStatus>()
//    val status: LiveData<ApiStatus>
//        get() = _status


    var cachedFilter: String = ""

    fun setFilter(filter: String) {
        dataSourceFactory.setFilter(if (cachedFilter.isEmpty()) filter else cachedFilter)
    }

    init {
        createLiveData()
    }

    fun createLiveData() {
        //_status.value = dataSourceFactory.getStatus()
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        this.stateLiveData = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build();
    }


}