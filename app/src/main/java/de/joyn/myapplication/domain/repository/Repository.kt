package de.joyn.myapplication.domain.repository

import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single

interface Repository {

    fun getFlowerUseCase(param:PhotoModel): Single<Models.BasePhoto>
}