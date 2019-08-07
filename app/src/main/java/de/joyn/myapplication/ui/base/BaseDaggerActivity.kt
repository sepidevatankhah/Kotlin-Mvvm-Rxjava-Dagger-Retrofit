package de.joyn.myapplication.ui.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import de.joyn.myapplication.di.viewmodel.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseDaggerActivity<S : BaseViewState, VM : BaseViewModel2<S>> : DaggerAppCompatActivity() {

    @Inject
    @JvmField
    var viewModelFactory: ViewModelFactory? = null
    val compositDesposable: CompositeDisposable = CompositeDisposable()


    protected lateinit var viewModel: VM

    override fun onStart() {
        super.onStart()
        startObserving()
    }

    override fun onDestroy() {
        compositDesposable.clear()
        super.onDestroy()
    }


    fun createViewModel(clazz: Class<VM>) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(clazz)
    }


    fun showToast(@StringRes string: Int) {
        if (string != 0)
            Toast.makeText(applicationContext, getString(string), Toast.LENGTH_LONG).show()
    }


    private fun startObserving() {
        viewModel.stateLiveData.observe(this, Observer { state -> handleState(state) })
    }

    abstract fun handleState(state: S)

    fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}