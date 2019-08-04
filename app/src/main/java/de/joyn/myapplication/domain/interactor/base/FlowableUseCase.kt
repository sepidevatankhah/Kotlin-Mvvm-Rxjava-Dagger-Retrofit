package de.joyn.myapplication.domain.interactor.base

import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import de.joyn.myapplication.domain.repository.Repository
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @param Responses The response value emitted by the Observable.
 * @param Params The request value.
 */
abstract class FlowableUseCase<Responses, Params>(useCaseExecutor: UseCaseExecutor,
                                                  postExecutionThread: PostExecutionThread,
                                                  protected var repository: Repository) :
    UseCase<Single<Responses>, Params>(useCaseExecutor, postExecutionThread) {

    open fun execute(params: Params?): Flowable<Responses> {
        return interact(params).applySchedulers()
    }

    protected abstract fun interact(params: Params?): Flowable<Responses>

}