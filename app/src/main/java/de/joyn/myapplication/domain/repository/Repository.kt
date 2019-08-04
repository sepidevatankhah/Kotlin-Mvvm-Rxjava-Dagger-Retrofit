package de.joyn.myapplication.domain.repository

import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single

interface Repository {

    fun getFlowerUseCase(query:String?): Single<List<Models.FlowerResponse>>
}