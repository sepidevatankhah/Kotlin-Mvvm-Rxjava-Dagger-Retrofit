package de.joyn.myapplication.ui.executer

import de.joyn.myapplication.domain.executer.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * provides Ui thread for [io.reactivex]
 */

class UiThreadExecutor : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}