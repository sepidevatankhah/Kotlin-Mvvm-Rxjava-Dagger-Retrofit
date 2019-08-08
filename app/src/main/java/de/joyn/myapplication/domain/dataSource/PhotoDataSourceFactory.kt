package de.joyn.myapplication.domain.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import de.joyn.myapplication.network.dto.Models
import javax.inject.Inject

class PhotoDataSourceFactory @Inject constructor(
    private val dataSource: PhotoPositionalDataSource
) : DataSource.Factory<Int, Models.PhotoResponse>() {

    //val photosDataSourceLiveData = MutableLiveData<PhotoPositionalDataSource>()
    fun setFilter(filter: String) {
        dataSource.setFilter(filter)
    }

    override fun create(): DataSource<Int, Models.PhotoResponse> {
       // photosDataSourceLiveData.postValue(dataSource)
            return dataSource
    }

}