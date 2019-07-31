package de.joyn.myapplication.domain.interactor

import de.joyn.myapplication.domain.entity.FlowerModel
import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import de.joyn.myapplication.domain.interactor.base.SingleUseCase
import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import javax.inject.Inject

class GetFlowerUseCase @Inject
constructor(
    useCaseExecutor: UseCaseExecutor,
    postExecutionThread: PostExecutionThread,
    repository: Repository
) : SingleUseCase<List<Models.FlowerResponse>, FlowerModel>(useCaseExecutor, postExecutionThread, repository) {
    override fun interact(params: FlowerModel?): Single<List<Models.FlowerResponse>> {
        return repository.getFlowerUseCase("")
    }
}