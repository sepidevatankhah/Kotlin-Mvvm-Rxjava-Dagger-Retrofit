package de.joyn.myapplication.domain.dataSource

import androidx.paging.PositionalDataSource
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.network.dto.Models
import javax.inject.Inject
import android.content.ClipData.Item
import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.ui.photoList.PhotoListViewState
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


class PhotoPositionalDataSource @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase
) : PositionalDataSource<Models.PhotoResponse>() {

    fun setFilter(filter : String?)   = filter
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun computeCount(): Int {
        // actual count code here
        return getPhotoUseCase.execute(PhotoModel()).blockingGet().total
    }

//    private fun loadRangeInternal(startPosition: Int, loadCount: Int): List<Models.PhotoResponse> {
//        // actual load code here
//
//        val pageNum = startPosition / loadCount + 1
//        var photoList: List<Models.PhotoResponse> =
//            getPhotoUseCase.execute(PhotoModel("", loadCount, pageNum)).blockingGet().response
//        Timber.d("photo list : %s ", photoList)
//        return photoList
//
//    }

    private fun loadRangeInternal(startPosition: Int, loadCount: Int) {
        // actual load code here

        val pageNum = startPosition / loadCount + 1
        val disposable = getPhotoUseCase.execute(PhotoModel("", loadCount, pageNum)).subscribe({ response ->
            // Timber.i("emitter size is"+response.size)
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Models.PhotoResponse>) {

        val pageNum = params.startPosition / params.loadSize + 1
        val disposable = getPhotoUseCase.execute(PhotoModel("", params.loadSize, pageNum)).subscribe({ response ->
            // Timber.i("emitter size is"+response.size)
            callback.onResult(response.response);
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Models.PhotoResponse>) {
        val totalCount = 200000//computeCount()
        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize = computeInitialLoadSize(params, position, totalCount)

        val pageNum = position / loadSize + 1
        val disposable = getPhotoUseCase.execute(PhotoModel("", loadSize, pageNum)).subscribe({ response ->
            // Timber.i("emitter size is"+response.size)
            callback.onResult(response.response, position, totalCount)
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }
}