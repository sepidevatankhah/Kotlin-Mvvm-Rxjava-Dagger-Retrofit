package de.joyn.myapplication.ui.fragments.photos

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import de.joyn.myapplication.domain.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.base.BaseViewModel
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 40

class PhotosViewModel @Inject constructor(
    private val dataSourceFactory: PhotoDataSourceFactory
) : BaseViewModel<PagedList<Models.PhotoResponse>>() {

    var cachedFilter: String = ""

    fun setFilter(filter: String) {
        dataSourceFactory.setFilter(if (cachedFilter.isEmpty()) filter else cachedFilter)
    }

    init {
        createLiveData()
    }

    fun createLiveData() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        this.stateLiveData = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build();
    }
//
//    fun getPhotoList(): LiveData<PagedList<Models.PhotoResponse>> {
//        return photoList
//    }
//
//    fun recreatePhotoList() {
//        photoList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
//    }
//
//    fun refresh() {
//        // dataSourceFactory.photosDataSourceLiveData.value!!.invalidate()
//    }

}