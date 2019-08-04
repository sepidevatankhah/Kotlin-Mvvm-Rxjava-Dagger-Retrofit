package de.joyn.myapplication.domain.repository

import de.joyn.myapplication.network.dto.BaseModel
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Flowable
import io.reactivex.Single

// This interface is in accordance to Dependency Inversion Principle by separating the higher
// repository class from lower database class.
interface Repository {

    fun getFlowerUseCase(query:String?): Flowable<BaseModel>
}