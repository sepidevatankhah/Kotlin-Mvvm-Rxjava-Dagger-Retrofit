package de.joyn.myapplication.data.repository

import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.RestApi
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val restApi: RestApi) : Repository {
    override fun getFlowerUseCase(ll: String): Single<List<Models.FlowerResponse>> {
        return Single.create { emitter ->
            restApi.getFlowers().subscribe({ response ->
                val list = ArrayList<Models.FlowerResponse>()

                response.response.also {
                    list.add(
                        Models.FlowerResponse(
                            it.id,
                            it.userName,
                            it.previewImageUrl,
                            it.viewNumber,
                            it.likeNumber,
                            it.downloadNumber
                        )
                    )
                }
                emitter.onSuccess(list)
            }, {
                emitter.onError(it)
            })
        }
    }


}