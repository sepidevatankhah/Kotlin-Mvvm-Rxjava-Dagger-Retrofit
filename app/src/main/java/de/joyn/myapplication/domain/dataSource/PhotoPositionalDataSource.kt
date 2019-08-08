package de.joyn.myapplication.domain.dataSource

import androidx.paging.PositionalDataSource
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.network.dto.Models
import javax.inject.Inject
import android.content.ClipData.Item
import de.joyn.myapplication.domain.entity.PhotoModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber


class PhotoPositionalDataSource @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase
) : PositionalDataSource<Models.PhotoResponse>(), Disposable {

    private var disposing = false
    override fun isDisposed(): Boolean {
        return disposing
    }

    override fun dispose() {
        disposing = true
        compositeDisposable.clear()
    }

    private var filter: String = ""
    fun setFilter(filter: String) {
        this.filter = filter
    }

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun computeCount(): Int {
        // actual count code here
        return getPhotoUseCase.execute(PhotoModel()).blockingGet().total
    }


    private fun loadRangeInternal(startPosition: Int, loadCount: Int) {
        // actual load code here

        val pageNum = startPosition / loadCount + 1
        val disposable = getPhotoUseCase.execute(PhotoModel(filter, loadCount, pageNum)).subscribe({ response ->
            // Timber.i("emitter size is"+response.size)
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Models.PhotoResponse>) {

        val pageNum = params.startPosition / params.loadSize + 1
        val disposable = getPhotoUseCase.execute(PhotoModel(filter, params.loadSize, pageNum)).subscribe({ response ->
            // Timber.i("emitter size is"+response.size)
            callback.onResult(response.response);
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Models.PhotoResponse>) {
        val totalCount = 2000
        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize = computeInitialLoadSize(params, position, totalCount)

        val pageNum = position / loadSize + 1
        val disposable = getPhotoUseCase.execute(PhotoModel(filter, loadSize, pageNum)).subscribe({ response ->
            callback.onResult(response.response, position, response.total)
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }
}