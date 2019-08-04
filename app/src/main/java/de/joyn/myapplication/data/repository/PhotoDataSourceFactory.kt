package de.joyn.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.network.RestApi
import de.joyn.myapplication.network.RestApiService
import de.joyn.myapplication.network.dto.Models
import io.reactivex.disposables.CompositeDisposable


/**
 * A simple data source factory which also provides a way to observe the last created data source.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
class PhotoDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val restApi: RestApiService
) :
    DataSource.Factory<Long, Models.PhotoResponse>() {
    val photosDataSourceLiveData = MutableLiveData<PhotoPageKeyedDataSource>()
    override fun create(): DataSource<Long, Models.PhotoResponse> {
        val photoDataSource = PhotoPageKeyedDataSource(restApi, compositeDisposable)
        photosDataSourceLiveData.postValue(photoDataSource)
        return photoDataSource
    }
}