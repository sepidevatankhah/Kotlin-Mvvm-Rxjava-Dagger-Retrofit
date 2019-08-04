package de.joyn.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import de.joyn.myapplication.network.RestApiService
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PhotoPageKeyedDataSource(
    private val restApi: RestApiService,
    private val compositeDisposable: CompositeDisposable
)
// private val retryExecutor: Executor
    : ItemKeyedDataSource<Long, Models.PhotoResponse>() {

    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, { throwable -> Timber.e(throwable.message) }))
        }
    }

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()


    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Models.PhotoResponse>
    ) {
        Timber.d("loadInitial")
        // update network states.
        // we also provide an initial load state to the listeners so that the UI can know when the
        // very first list is loaded.
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        //get the initial photos from the api
        compositeDisposable.add(
            restApi.getPagedPhotos("",  params.requestedLoadSize)
                .subscribe({ photos ->
                    Timber.d("loadInitial photos : %s", photos)
                    // clear retry since last request succeeded
                    setRetry(null)

                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(photos.response)
                }, { throwable ->
                    // keep a Completable for future retry
                    setRetry(Action { loadInitial(params, callback) })
                    val error = NetworkState.error(throwable.message)
                    // publish the error
                    networkState.postValue(error)
                    initialLoad.postValue(error)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Models.PhotoResponse>) {
        // set network value to loading.
        networkState.postValue(NetworkState.LOADING)

        //get the users from the api after id
        compositeDisposable.add(
            restApi.getPagedPhotos(
               "",
                params.requestedLoadSize
            ).subscribe({ photos ->

                Timber.d("loadAfter photos : %s", photos)
                // clear retry since last request succeeded
                setRetry(null)
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(photos.response)
            }, { throwable ->
                // keep a Completable for future retry
                setRetry(Action { loadAfter(params, callback) })
                // publish the error
                networkState.postValue(NetworkState.error(throwable.message))
            })
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Models.PhotoResponse>) {
        // ignored, since we only ever append to our initial load
    }

    override fun getKey(item: Models.PhotoResponse): Long {
        return item.id
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }




}