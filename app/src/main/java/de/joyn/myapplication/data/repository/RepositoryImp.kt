package de.joyn.myapplication.data.repository

import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.RestApi
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val restApi: RestApi) : Repository {
    override fun getFlowerUseCase(ll: String): Single<List<Models.FlowerResponse>> {
        return Single.create { emitter ->
            restApi.getFlowers().subscribe({ response ->
                Timber.d("response : " + response.response.size)
                emitter.onSuccess(response.response)
            }, {
                emitter.onError(it)
            })
        }
    }


}