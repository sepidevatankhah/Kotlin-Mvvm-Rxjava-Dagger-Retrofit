package de.joyn.myapplication.domain.executer

import io.reactivex.Scheduler

/**
 * Represents an asynchronous execution for [interactor.ObservableUseCase].
 * It's useful to execute use cases out of
 * the UI thread to prevent it from freezing.
 */
interface UseCaseExecutor {
    val scheduler: Scheduler
}