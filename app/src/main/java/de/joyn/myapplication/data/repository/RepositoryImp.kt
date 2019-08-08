package de.joyn.myapplication.data.repository

import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.RestApi
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val restApi: RestApi) : Repository {
    override fun getFlowerUseCase(param: PhotoModel): Single<Models.BasePhoto> {
        return Single.create { emitter ->
            restApi.getPhotos(param.query, param.pageSize, param.pageNum).subscribe({ response ->
                Timber.d("response : %s", response)
                emitter.onSuccess(response)
            }, {
                emitter.onError(it)
            })
        }
    }


}