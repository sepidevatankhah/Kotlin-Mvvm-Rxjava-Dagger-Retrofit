package de.joyn.myapplication.ui.fragments.photos

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import de.joyn.myapplication.domain.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.network.dto.Models
import de.joyn.myapplication.ui.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 40

class PhotosViewModel @Inject constructor(
    private val dataSourceFactory: PhotoDataSourceFactory
) : BaseViewModel() {


    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPageSize(PAGE_SIZE)
        .build()

    //var photoList: LiveData<PagedList<Models.PhotoResponse>>


    val photoList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    //Timber.d("photoList : %s", photoList)
    //getPhotos("flowers")

}