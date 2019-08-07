package de.joyn.myapplication.domain.interactor

import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import de.joyn.myapplication.domain.interactor.base.SingleUseCase
import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import javax.inject.Inject

class GetPhotoUseCase @Inject
constructor(
    useCaseExecutor: UseCaseExecutor,
    postExecutionThread: PostExecutionThread,
    repository: Repository
) : SingleUseCase<Models.BasePhoto, PhotoModel>(useCaseExecutor, postExecutionThread, repository) {
    override fun interact(params: PhotoModel): Single<Models.BasePhoto> {
        return repository.getFlowerUseCase(params)
    }
}