package de.joyn.myapplication.domain.dataSource

import androidx.paging.DataSource
import de.joyn.myapplication.network.dto.Models
import javax.inject.Inject

class PhotoDataSourceFactory @Inject constructor(
    private val dataSource: PhotoPositionalDataSource
) : DataSource.Factory<Int, Models.PhotoResponse>() {

    fun setFilter(filter : String?) : String?  = filter
    override fun create(): DataSource<Int, Models.PhotoResponse> {
        dataSource
       return dataSource
    }

}