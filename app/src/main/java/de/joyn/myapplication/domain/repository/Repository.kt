package de.joyn.myapplication.domain.repository

import de.joyn.myapplication.network.dto.BaseModel
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Flowable
import io.reactivex.Single

interface Repository {

    fun getFlowerUseCase(query:String?): Flowable<BaseModel>
}