package de.joyn.myapplication.data.repository

import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.RestApi
import de.joyn.myapplication.network.dto.BaseModel
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val restApi: RestApi) : Repository {
    override fun getFlowerUseCase(query: String?): Flowable<BaseModel> {
        return restApi.getPhotos(query)
    }

}